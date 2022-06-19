package com.mycompany.invoice.invoice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvoiceControllerWeb {
/*
    @Autowired
    private InvoiceServiceInterface invoiceService;

    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }
    @PostMapping("/create")
    public String createInvoice(@Valid @ModelAttribute InvoiceForm invoiceForm, BindingResult results) {
        if(results.hasErrors()) {
            return "invoice-create-form";
        } else {
            Invoice invoice = new Invoice();
            Customer customer = new Customer(invoiceForm.getCustomerName());
            invoice.setCustomer(customer);
            // invoice.setOrderNumber(invoiceForm.getOrderNumber());
            Address address = new Address();
            address.setStreet(invoiceForm.getStreetName());
            address.setStreetNumber(invoiceForm.getStreetNumber());
            address.setZipCode(invoiceForm.getZipCode());
            address.setCity(invoiceForm.getCity());
            address.setCountry(invoiceForm.getCountry());
            customer.setAddress(address);
            invoiceService.createInvoice(invoice);
            return "invoice-created";
        }
    }
*/
    @GetMapping("/home")
    public String displayHome() {
        System.out.println("La méthode display home a été appelée");
        return "invoice-home";
    }
/*
    @GetMapping("/create-form")
    public String displayInvoiceCreateForm(@ModelAttribute InvoiceForm invoiceForm) {
        return "invoice-create-form";
    }

 */
}
