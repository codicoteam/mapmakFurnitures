package com.clinpride.SecurityPostgres.Orders.services;

import com.clinpride.SecurityPostgres.Orders.models.OrderModels;
import com.clinpride.SecurityPostgres.Orders.repo.OrderRepo;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class OrderServicesImp implements OrderServices{
    private final JavaMailSender javaMailSender;

    private final OrderRepo orderRepo;

    @Override
    public String createOrder(OrderModels orderObject) {
        try {
            StringBuilder productNames = new StringBuilder();
            for (ProductsModel product : orderObject.getProducts()) {
                productNames.append(product.getProductName()).append(", ");
            }

            orderRepo.save(orderObject);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(orderObject.getCustomerEmail());
            message.setSubject("Order Confirmation - Successful Order Placement");
            message.setText("\n" +
                    "We are delighted to inform you that your order has been successfully placed on our system. Thank you for choosing our services and placing your trust in us.\n" +
                    "\n" +
                    "Order Details:\n" +
                    "Order Number: " +orderObject.getOrderId() + " \n" +
                    "Order Date: " +orderObject.getDateNow() + " \n" +
                    "Product(s) Ordered:[ " +productNames + "] \n" +
                    "Total Amount: " +orderObject.getOrderTotal() + " \n" +
                    "\n" +
                    "Our team is now processing your order, and we assure you that we will handle it with utmost care and efficiency. We aim to provide you with an exceptional experience.\n" +
                    "\n" +
                    "Within the next 24 hours, one of our dedicated representatives will reach out to you personally to confirm the order details, address any questions or concerns you may have, and provide you with the expected delivery timeline.");
            javaMailSender.send(message);

// Create the email message for the owner
            SimpleMailMessage ownerMessage = new SimpleMailMessage();
            ownerMessage.setTo("zpmakaza@gmail.com"); // Replace with the owner's email address
            ownerMessage.setSubject("New Order Notification");
            ownerMessage.setText("An order has been placed by a customer.\n\n" +
                    "Order Details:\n" +
                    "Order Number: " + orderObject.getOrderId() + "\n" +
                    "Order Date: " + orderObject.getDateNow() + "\n" +
                    "Customer Email: " + orderObject.getCustomerEmail() + "\n" +
                    "Product(s) Ordered: " + productNames + "\n" +
                    "Total Amount: " + orderObject.getOrderTotal() + "\n"+
                    "For more details please check your Dashboard on MapMak Furniture App:\n"
            );



            javaMailSender.send(ownerMessage);
            return "Order saved successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Order saving failed";
        }
    }


    @Override
    public OrderModels getOneOrderById(String id) {
        return orderRepo.findById(id).orElse(null);
    }

    @Override
    public List<OrderModels> getOrderByCustomerEmail(String Email) {
        return orderRepo.findByCustomerEmail(Email);
    }

    @Override
    public OrderModels getOneOrderByOrderId(String orderId) {
        return orderRepo.findByOrderId(orderId).orElse(null);

    }
    @Override
    public Optional<OrderModels> editByOrderId(String Id, OrderModels updatedOrder) {
        Optional<OrderModels> existingOrder = orderRepo.findById(Id);
        if (existingOrder.isPresent()) {
            OrderModels order = existingOrder.get();
            // Update the fields of the existing order with the updatedOrder values
            order.setProducts(updatedOrder.getProducts());
            order.setOrderTotal(updatedOrder.getOrderTotal());
            order.setCustomerEmail(updatedOrder.getCustomerEmail());
            order.setCustomerPhoneNumber(updatedOrder.getCustomerPhoneNumber());
            order.setCustomerLocation(updatedOrder.getCustomerLocation());
            order.setOrderTotalMoney(updatedOrder.getOrderTotalMoney());
            order.setShowOrder(updatedOrder.getShowOrder());
            order.setBooleanOrder(updatedOrder.getBooleanOrder());
            order.setStatus(updatedOrder.getStatus());
            order.setComment(updatedOrder.getComment());
            order.setPostalCode(updatedOrder.getPostalCode());
            order.setMapLatitudes(updatedOrder.getMapLatitudes());
            order.setMapLongitudes(updatedOrder.getMapLongitudes());

            return Optional.of(orderRepo.save(order));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteOrder(String id) {

        Optional<OrderModels> productOptional = orderRepo.findById(id);
        if (productOptional.isPresent()) {
            orderRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
