package com.mycompany.invoice.invoice.api;

import com.mycompany.invoice.core.entity.customer.Address;
import com.mycompany.invoice.core.entity.customer.Customer;
import com.mycompany.invoice.core.entity.invoice.Invoice;
import com.mycompany.invoice.invoice.service.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/invoice")
public class InvoiceResource {

    @Autowired
    private InvoiceServiceInterface invoiceService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }

    public WebClient.Builder getWebClientBuilder() {
        return webClientBuilder;
    }

    public void setWebClientBuilder(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    /*
            @PostMapping()
            public Invoice create(@RequestBody Invoice invoice) {
                return invoiceService.createInvoice(invoice);
            }
        */
    @GetMapping("")
    public ParallelFlux<Invoice> list() {

        List<Mono<Invoice>> invoiceMono = new ArrayList<>();

        final WebClient webClient = webClientBuilder.baseUrl("http://customer-service").build();

        var invoices = invoiceService.list();
        invoices.forEach(invoice -> {
            invoiceMono.add(webClient.get()
                    .uri("/customer/" + invoice.getIdCustomer())
                    .retrieve()
                    .bodyToMono(Customer.class)
                    .map(
                            customer -> {
                                invoice.setCustomer(customer);
                                return invoice;
                            }
                    )
            );
        });

        final Flux<Invoice> invoiceFlux = Flux.concat(invoiceMono);

        return invoiceFlux.parallel().runOn(Schedulers.elastic());
    }

    @GetMapping("/{id}")
    public Invoice get(@PathVariable("id") String number) {

        final WebClient webClient = webClientBuilder.baseUrl("http://customer-service").build();

        var invoice = invoiceService.getInvoiceByNumber(number);
        var customer = webClient
                .get()
                .uri("/customer/" + invoice.getIdCustomer())
                .retrieve()
                .bodyToMono(Customer.class)
                .block();
        invoice.setCustomer(customer);
        var address = webClient
                .get()
                .uri("/address/" + customer.getAddress().getId())
                .retrieve()
                .bodyToMono(Address.class)
                .block();
        customer.setAddress(address);
        return invoice;
    }

}
