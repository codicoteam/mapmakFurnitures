package com.clinpride.SecurityPostgres.Hiring.Services;

import com.clinpride.SecurityPostgres.Hiring.Repo.HiringRepo;
import com.clinpride.SecurityPostgres.Hiring.models.HiringModel;
import com.clinpride.SecurityPostgres.Orders.models.OrderModels;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HiringServicesImpl implements HiringServices{
    private final HiringRepo hiringRepo;
    private final JavaMailSender javaMailSender;

    @Override
    public String HireProducts(HiringModel hiringModel) {
        try {
            StringBuilder productNames = new StringBuilder();
            for (ProductsModel product : hiringModel.getProducts()) {
                productNames.append(product.getProductName()).append(", ");
            }

            hiringRepo.save(hiringModel);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(hiringModel.getCustomerEmail());
            message.setSubject("Bookinng Furnitures Confirmation - Successful Order Placement");
            message.setText("\n" +
                    "We are delighted to inform you that your order has been successfully placed on our system. Thank you for choosing our services and placing your trust in us.\n" +
                    "\n" +
                    "Order Details:\n" +
                    "Order Number: " + hiringModel.getHiringId() + " \n" +
                    "Order Date: " + hiringModel.getDateNow() + " \n" +
                    "Product(s) Ordered: [" + productNames + "] \n" +
                    "Total Amount: " + hiringModel.getHiringTotal() + " \n" +
                    "\n" +
                    "Our team is now processing your order, and we assure you that we will handle it with utmost care and efficiency. We aim to provide you with an exceptional experience.\n" +
                    "\n" +
                    "Within the next 24 hours, one of our dedicated representatives will reach out to you personally to confirm your payment method.\n" +
                    "\n" +
                    "If you have any further questions or need assistance, please feel free to contact us. We are here to help.\n" +
                    "\n" +
                    "Thank you once again for choosing MapMak Furniture. We look forward to serving you and providing a seamless furniture booking experience.\n" +
                    "\n" +
                    "Best regards,\n" +
                    "MapMak Furniture");

            javaMailSender.send(message);

// Create the email message for the owner
            SimpleMailMessage ownerMessage = new SimpleMailMessage();
            ownerMessage.setTo("zpmakaza@gmail.com"); // Replace with the owner's email address
            ownerMessage.setSubject("New Order Notification");
            ownerMessage.setText("A hiring order has been made by a customer.\n\n" +
                    "Booking Details:\n" +
                    "Booking Number: " + hiringModel.getHiringId() + "\n" +
                    "Booking Date: " + hiringModel.getDateNow() + "\n" +
                    "Customer Email: " + hiringModel.getCustomerEmail() + "\n" +
                    "Product(s) Booked: " + productNames + "\n" +
                    "Hiring Description: " + hiringModel.getHiringDescription() + "\n" +
                    "Total Amount: " + hiringModel.getHiringTotal() + "\n"+
                    "Please check your Dashboard on the MapMak Furniture App for more details:\n"
            );



            javaMailSender.send(ownerMessage);
            return "Hiring saved successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Order saving failed";
        }
    }

    @Override
    public HiringModel getOneHiringById(String id) {
        return hiringRepo.findById(id).orElse(null);
    }

    @Override
    public List<HiringModel> getOrderByCustomerEmail(String Email) {
        return hiringRepo.findByCustomerEmail(Email);
    }

    @Override
    public HiringModel getOneHiringByOrderId(String orderId) {
        return hiringRepo.findByHiringId(orderId).orElse(null);
    }

    @Override
    public Optional<HiringModel> editByHiringId(String Id, HiringModel hiringModel) {

        Optional<HiringModel> existingOrder = hiringRepo.findById(Id);
        if (existingOrder.isPresent()) {
            HiringModel hiringOrder = existingOrder.get();
            // Update the fields of the existing order with the updatedOrder values
            hiringOrder.setProducts(hiringModel.getProducts());
            hiringOrder.setHiringTotal(hiringModel.getHiringTotal());
            hiringOrder.setCustomerEmail(hiringModel.getCustomerEmail());
            hiringOrder.setCustomerPhoneNumber(hiringModel.getCustomerPhoneNumber());
            hiringOrder.setVenueOfEvent(hiringModel.getVenueOfEvent());
            hiringOrder.setHiringTotalMoney(hiringModel.getHiringTotalMoney());
            hiringOrder.setDateOfEvent(hiringModel.getDateOfEvent());
            hiringOrder.setNameOfEvent(hiringModel.getNameOfEvent());
            hiringOrder.setStatus(hiringModel.getStatus());
            hiringOrder.setHomeAddress(hiringModel.getHomeAddress());
            hiringOrder.setDuration(hiringModel.getDuration());
            hiringOrder.setProducts(hiringModel.getProducts());
            hiringOrder.setEndOfEventTime(hiringModel.getEndOfEventTime());
            hiringOrder.setNationalId(hiringModel.getNationalId());
            hiringOrder.setBooleanStatus(hiringModel.getBooleanStatus());
            hiringOrder.setPassportNumber(hiringModel.getPassportNumber());
            hiringOrder.setNameOfRecipient(hiringModel.getNameOfRecipient());
            hiringOrder.setEmailOfRecipient(hiringModel.getEmailOfRecipient());
            hiringOrder.setHiringDescription(hiringModel.getHiringDescription());

            return Optional.of(hiringRepo.save(hiringOrder));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteHiring(String id) {

        Optional<HiringModel> productOptional = hiringRepo.findById(id);
        if (productOptional.isPresent()) {
            hiringRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }    }
}
