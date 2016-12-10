package com.rakin.echo;

public class DefaultEchoService implements EchoService{

	private final String echoFormat;

	public DefaultEchoService(String echoFormat) {
		this.echoFormat = (echoFormat != null) ? echoFormat : "%s";
}
	@Override
	public String getMessage(String message) {
		// TODO Auto-generated method stub
		return String.format(this.echoFormat, message);
	}

}
