package org.example.netty.chat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;

public class ChatClientHandler extends ChannelInboundMessageHandlerAdapter<String> {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, String msg)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}

}
