package bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsString {
String response;

public JsString() {
	// TODO Auto-generated constructor stub
}


@XmlElement
public String getResponse() {
	return response;
}

public void setResponse(String response) {
	this.response = response;
}


}
