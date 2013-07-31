package it.italiangrid.portal.registration.dirac.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This Class transform a DiracTask in JSON format and viceversa.
 * 
 * @author dmichelotto
 * 
 */
public class DiracTaskJSON {

	/**
	 * The DiracTask do create or transform.
	 */
	private DiracTask diracTask;

	/**
	 * Constructor for DiracTask to JSON transformation.
	 * 
	 * @param diracTask
	 *            - the DiracTask to transform in JSON.
	 */
	public DiracTaskJSON(DiracTask diracTask) {

		this.diracTask = diracTask;

	}

	/**
	 * Constructor for JSON to DiracTask transformation.
	 * 
	 * @param encodedString
	 *            - the JSON string to transform in DiracTask.
	 */
	public DiracTaskJSON(String encodedString) {
		diracTask = new DiracTask();
		decode(encodedString);
	}

	/**
	 * Getter method.
	 * 
	 * @return the diracTask.
	 */
	public DiracTask getDiracTask() {
		return diracTask;
	}

	/**
	 * Setter method.
	 * 
	 * @param diracTask
	 *            - the DiracTask to set.
	 */
	public void setDiracTask(DiracTask diracTask) {
		this.diracTask = diracTask;
	}

	/**
	 * Transform DiracTask in JSON.
	 * 
	 * @return the JSON encoded String.
	 */
	public String getEncodedDiracTask() {
		return encode();
	}

	/**
	 * Encoder method.
	 */
	private String encode() {

		Map<String, String> obj = new LinkedHashMap<String, String>();
		obj.put("userCert", diracTask.getUserCert());
		obj.put("userKey", diracTask.getUserKey());
		obj.put("password", diracTask.getPassword());
		obj.put("email", diracTask.getEmail());
		obj.put("dn", diracTask.getDn());
		obj.put("username", diracTask.getUsername());

		return JSONValue.toJSONString(obj);
	}

	/**
	 * Decoder method.
	 * 
	 * @param encodedString
	 *            - the JSON String to decode.
	 */
	@SuppressWarnings("unchecked")
	private void decode(String encodedString) {
		JSONParser parser = new JSONParser();
		ContainerFactory containerFactory = new ContainerFactory() {
			public List<String> creatArrayContainer() {
				return new LinkedList<String>();
			}

			public Map<String, String> createObjectContainer() {
				return new LinkedHashMap<String, String>();
			}

		};

		Map<String, String> json = new LinkedHashMap<String, String>();
		Object obj;
		try {

			obj = parser.parse(encodedString, containerFactory);

			if (obj instanceof LinkedHashMap)
				json = (Map<String, String>) obj;
			else
				throw new ParseException(
						ParseException.ERROR_UNEXPECTED_EXCEPTION);

		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}

		diracTask.setDn(json.get("dn"));
		diracTask.setEmail(json.get("email"));
		diracTask.setPassword(json.get("password"));
		diracTask.setUserCert(json.get("userCert"));
		diracTask.setUserKey(json.get("userKey"));
		diracTask.setUsername(json.get("username"));
	}

}
