package portal.registration.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import com.liferay.portal.model.User;

public class GuseNotifyUtil {

	private static String[] VALUES = { "wfchg_mess", "wfchg_enab",
			"quota_mess", "quota_enab", "email_addr", "email_enab",
			"email_subj" };

	private static final Logger log = Logger.getLogger(GuseNotifyUtil.class);

	public GuseNotify readNotifyXML(User user) {

		String filename = System.getProperty("java.io.tmpdir") + "/users/"
				+ user.getUserId() + "/.notify.xml";
		File notifyFile = new File(filename);
		GuseNotify guseNotify = new GuseNotify(user.getFirstName());

		log.info(filename);
		
		try {

			if (notifyFile.exists()) {
				log.info("esiste");
				SAXBuilder builder = new SAXBuilder();
				Document doc = (Document) builder.build(notifyFile);
				Element rootNode = doc.getRootElement();
				List<String> keys = new ArrayList<String>();
				keys = Arrays.asList(VALUES);

				String[] guseNotifyArray = new String[7];

				List<Element> childs = rootNode.getChildren();
				for (Element element : childs) {

					String selectedKey = element.getAttributeValue("key");
					guseNotifyArray[keys.indexOf(selectedKey)] = element
							.getAttributeValue("value");

				}

				guseNotify = arrayToGuseNotify(guseNotifyArray);

			} else {

				log.info("non esiste");
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return guseNotify;
	}

	public boolean writeNotifyXML(User user, GuseNotify guseNotify) {

		String filename = System.getProperty("java.io.tmpdir") + "/users/"
				+ user.getUserId() + "/.notify.xml";

		File directory = new File(filename.replace("/.notify.xml", ""));
		File notifyFile = new File(filename);
		log.info(filename);

		List<String> keys = new ArrayList<String>();
		keys = Arrays.asList(VALUES);

		guseNotify.setQuotaEnab((guseNotify.getQuotaEnab().equals("true") ? "1"
				: "0"));
		String[] guseNotifyArray = guseNotifyToArray(guseNotify);
		guseNotifyArray[keys.indexOf("email_addr")] = user.getEmailAddress();

		try {

			if (!directory.exists())
				directory.mkdirs();

			Document doc;

			if (notifyFile.exists()) {

				SAXBuilder builder = new SAXBuilder();
				doc = (Document) builder.build(notifyFile);
				Element rootNode = doc.getRootElement();
				List<Element> childs = rootNode.getChildren();

				for (Element element : childs) {

					String selectedKey = element.getAttributeValue("key");
					element.getAttribute("value").setValue(
							guseNotifyArray[keys.indexOf(selectedKey)]);

				}

			} else {

				notifyFile.createNewFile();

				Element root = new Element("notify");
				doc = new Document(root);

				for (int i = 0; i < keys.size(); i++) {

					Element prop = new Element("prop");
					prop.setAttribute(new Attribute("key", keys.get(i)));
					prop.setAttribute(new Attribute("value", guseNotifyArray[i]));

					doc.getRootElement().addContent(prop);
				}

			}

			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(filename));

			return true;

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}

	private String[] guseNotifyToArray(GuseNotify guseNotify) {

		String[] guseNotifyArray = { guseNotify.getWfchgMess(),
				guseNotify.getWfchgEnab().equals("true") ? "1" : "0",
				guseNotify.getQuotaMess(),
				guseNotify.getQuotaEnab().equals("true") ? "1" : "0",
				guseNotify.getEmailAddr(),
				guseNotify.getEmailEnab().equals("true") ? "1" : "0",
				guseNotify.getEmailSubj() };
		return guseNotifyArray;
	}

	private GuseNotify arrayToGuseNotify(String[] array) {

		GuseNotify guseNotify = new GuseNotify();

		guseNotify.setWfchgMess(array[0]);
		guseNotify.setWfchgEnab(array[1]);
		guseNotify.setQuotaMess(array[2]);
		guseNotify.setQuotaEnab(array[3]);
		guseNotify.setEmailAddr(array[4]);
		guseNotify.setEmailEnab(array[5]);
		guseNotify.setEmailSubj(array[6]);

		return guseNotify;
	}

}
