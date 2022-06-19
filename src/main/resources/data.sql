DELETE FROM invoice_line;
DELETE FROM invoice;

INSERT INTO invoice (ORDER_NUMBER,ID_CUSTOMER) VALUES
                                                   ('AA123456789A',1),
                                                   ('AA123456789B',1),
                                                   ('BB123456789A',2),
                                                   ('CC123456789A',3);

INSERT INTO invoice_line (ID_PRODUCT,QUANTITY,INVOICE_NUMBER) VALUES
                                                                  (1,10,(select INVOICE_NUMBER from invoice where ORDER_NUMBER='AA123456789A')),
                                                                  (3,1,(select INVOICE_NUMBER from invoice where ORDER_NUMBER='AA123456789A')),
                                                                  (2,1,(select INVOICE_NUMBER from invoice where ORDER_NUMBER='AA123456789B')),
                                                                  (1,22,(select INVOICE_NUMBER from invoice where ORDER_NUMBER='BB123456789A')),
                                                                  (4,2,(select INVOICE_NUMBER from invoice where ORDER_NUMBER='BB123456789A')),
                                                                  (1,5,(select INVOICE_NUMBER from invoice where ORDER_NUMBER='CC123456789A'));