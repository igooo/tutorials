package org.igooo.ai.receipt.frontend;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import org.igooo.ai.receipt.ai.ChatService;

@Route
class MainView extends VerticalLayout {
	private final ChatService chatService;

	MainView(ChatService chatService) {
		this.chatService = chatService;

		initFrontend();
	}

	private void initFrontend() {
		var text = new Text("영수증 업로드");
		var buffer = new MemoryBuffer();
		var uploadBtn = new Upload(buffer);
		uploadBtn.setAcceptedFileTypes("image/*");
		uploadBtn.addSucceededListener((event) -> {
			var receipt = this.chatService.prompt("첨부된 영수증을 읽고 제공한 포멧으로 응답해주세요.", event.getMIMEType(), buffer.getInputStream(), Receipt.class);
			showReceipt(receipt);
		});
		add(text, uploadBtn);
	}

	private void showReceipt(Receipt receipt) {
		var items = new Grid<>(Receipt.Item.class);
		items.setItems(receipt.items());

		add(
				new H3("영수증 상세"),
				new Paragraph("상품: " + receipt.merchant()),
				new Paragraph("전체: " + receipt.total()),
				items
		);
	}
}
