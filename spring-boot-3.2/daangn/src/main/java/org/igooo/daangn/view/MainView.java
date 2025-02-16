package org.igooo.daangn.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import org.igooo.daangn.ai.ChatService;

@Route
class MainView extends VerticalLayout {
    private final ChatService chatService;

    MainView(ChatService chatService) {
        this.chatService = chatService;

        initView();
    }

    private void initView() {
        var titleView = new TextField("제목", "글 제목");

        var buffer = new MemoryBuffer();
        var imageUploadBtn = new Upload(buffer);
        imageUploadBtn.setAcceptedFileTypes("image/*");
        imageUploadBtn.addSucceededListener((event) -> {
            var product = this.chatService.prompt("첨부된 이미지를 읽고 제품 이름과 브랜드 정보를 알려줘.",
                    event.getMIMEType(), buffer.getInputStream(), Product.class);
            titleView.setValue(product.brand() + " " + product.name());
        });

        var textArea = new TextArea("자세한 설명");
        var saleInfo = new Text("");
        var sendBtn = new Button("작성 완료");
        sendBtn.addClickListener((event) -> {
            var sale = this.chatService.prompt("""
                            판매 정보를 확인하여 판매 장소와 판매 상품 정보를 알려줘.
                            정보가 부정확하면 null로 응답해줘.
                            판매 정보 : 
                            """
                            + textArea.getValue()
                    , Sale.class);
            saleInfo.setText("판매 장소 : " + sale.address() + ", 판매 상품 : " + sale.productName());
        });

        add(imageUploadBtn, titleView, textArea, saleInfo, sendBtn);
    }
}

