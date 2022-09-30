package com.mindhub.skywalkies.Service.implementations;


import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.*;
import com.mindhub.skywalkies.Service.PDFService;
import com.mindhub.skywalkies.Service.ProductService;
import com.mindhub.skywalkies.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.number.money.MonetaryAmountFormatter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class PDFServiceImplement implements PDFService {
    private static Font titleFont = new Font(Font.FontFamily.HELVETICA, 18,
            Font.BOLD);
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 14,
            Font.BOLD, BaseColor.WHITE);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 12,
            Font.NORMAL);
    private NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();

    @Autowired
    private ProductService productService;
    @Override
    public void generatePDF(HttpServletResponse response,  Bill bill) {
        try {
            Document document = new Document(PageSize.A4);

            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            document.setMargins(1, 1, 1, 1);

            Set<Client_order> client_orders = bill.getClient_orders();
            Client client = bill.getClient();
            String firstName = client.getFirstName().toUpperCase().charAt(0) + client.getFirstName().substring(1, client.getFirstName().length()).toLowerCase();
            String lastName = client.getLastName().toUpperCase().charAt(0) + client.getLastName().substring(1, client.getLastName().length()).toLowerCase();

            Paragraph espacing = new Paragraph("  ");
            /*HEADER*/

            /*LOGO*/
            Image img = Image.getInstance("./src/main/resources/static/assets/img/skyWalkiesIsoLogo.png");
            img.scaleAbsoluteWidth(200);
            img.scaleAbsoluteHeight(80);
            img.setAlignment(Element.ALIGN_LEFT);

            List infoList = new List();

            float col = 280f;
            float colWidth[] = {col, col};
            PdfPTable table = new PdfPTable(colWidth);
            PdfPCell imgCell = new PdfPCell(img);
            PdfPCell infoCell = new PdfPCell();


            /*TITLES*/

            ListItem title = new ListItem("Payment Summary", titleFont);
            title.setSpacingAfter(3);
            title.setAlignment(Element.ALIGN_LEFT);
            title.setListSymbol(new Chunk(" "));


            ListItem subTitle = new ListItem("Bill N°: " + bill.getTicketNumber(), subFont);
            subTitle.setAlignment(Element.ALIGN_LEFT);
            subTitle.setSpacingAfter(1);
            subTitle.setListSymbol(new Chunk(" "));

            ListItem clientName = new ListItem("Client: " + firstName + " " + lastName, subFont);
            clientName.setAlignment(Element.ALIGN_LEFT);
            clientName.setSpacingAfter(1);
            clientName.setListSymbol(new Chunk(" "));

            ListItem date = new ListItem("Date: " + LocalDate.now(), subFont);
            date.setSpacingAfter(6);
            date.setAlignment(Element.ALIGN_LEFT);
            date.setListSymbol(new Chunk(" "));

            infoList.add(title);
            infoList.add(subTitle);
            infoList.add(clientName);
            infoList.add(date);
            infoCell.addElement(infoList);
            infoCell.setBackgroundColor(new BaseColor(Color.lightGray));
            imgCell.setBackgroundColor(new BaseColor(Color.lightGray));
            imgCell.setBorder(0);
            infoCell.setBorder(0);
            table.addCell(imgCell);
            table.addCell(infoCell);
            table.setSpacingBefore(10);


            /*HEADERS*/

            PdfPTable pdfPTable = new PdfPTable(5);

            java.util.Set<String> headersList = Set.of("Name", "Quantity", "Size", "Price", "Total");

            headersList.forEach(header -> {
                PdfPCell cell = new PdfPCell(new Phrase(header));
                cell.setBorder(0);
                cell.setBackgroundColor(new BaseColor(Color.lightGray));
                cell.setMinimumHeight(2);
                pdfPTable.addCell(cell);
            });


            /*TABLE OF PRODUCTS*/

            if(bill.getClient_orders()!=null){
                bill.getClient_orders().forEach(client_order -> {
                    client_order.getOrdered_products().forEach(ordered_product -> {
                                Product product = ordered_product.getProduct();



                PdfPCell pdfPCell6 = new PdfPCell(new Paragraph(String.valueOf(product.getName()), subFont));
                PdfPCell pdfPCell7 = new PdfPCell(new Paragraph(String.valueOf(ordered_product.getQuantity()) , subFont));
                PdfPCell pdfPCell8 = new PdfPCell(new Paragraph(String.valueOf(ordered_product.getSize()) , subFont));
                PdfPCell pdfPCell9 = new PdfPCell(new Paragraph("$" + String.valueOf(product.getPrice()), subFont));
                PdfPCell pdfPCell10 = new PdfPCell(new Paragraph("$" + String.valueOf(product.getPrice()*ordered_product.getQuantity()), subFont));
                pdfPCell6.setMinimumHeight(3);
                pdfPCell7.setMinimumHeight(3);
                pdfPCell8.setMinimumHeight(3);
                pdfPCell9.setMinimumHeight(3);
                pdfPCell10.setMinimumHeight(3);



                pdfPTable.addCell(pdfPCell6);
                pdfPTable.addCell(pdfPCell7);
                pdfPTable.addCell(pdfPCell8);
                pdfPTable.addCell(pdfPCell9);
                pdfPTable.addCell(pdfPCell10);
            });});};


            PdfPTable footerTable = new PdfPTable(colWidth);
            PdfPCell ivaCell = new PdfPCell(new Phrase("IVA: " + String.valueOf(moneyFormatter.format(bill.getIva())), subFont ));
            PdfPCell totalCell = new PdfPCell(new Phrase("Total: " + String.valueOf(moneyFormatter.format(bill.getTotal())), subFont));
            totalCell.setBorder(0);
            totalCell.setBackgroundColor(new BaseColor(Color.LIGHT_GRAY));
            totalCell.setMinimumHeight(5);
            ivaCell.setBorder(0);
            ivaCell.setBackgroundColor(new BaseColor(Color.LIGHT_GRAY));
            ivaCell.setMinimumHeight(5);
            footerTable.addCell(ivaCell);
            footerTable.addCell(totalCell);

            document.add(table);
            document.add(espacing);
            document.add(pdfPTable);
            document.add(espacing);
            document.add(footerTable);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
