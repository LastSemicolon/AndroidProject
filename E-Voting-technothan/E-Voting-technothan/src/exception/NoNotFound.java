package exception;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement
public class NoNotFound extends Exception {
	
	
private String error_msg;
	
@XmlElement
public String getError_msg() {
	return "no_number";
}

}
