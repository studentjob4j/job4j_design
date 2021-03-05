package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "jsonexample")
@XmlAccessorType(XmlAccessType.FIELD)
public class JsonExample {

    @XmlAttribute
    private boolean value;
    @XmlAttribute
    private int data;
    @XmlAttribute
    private String text;
    private Contact contact;
    @XmlElementWrapper(name = "arrays")
    @XmlElement(name = "array")
    private String[] arrays;

    public JsonExample(boolean value, int data, String text, Contact contact, String[] array) {
        this.value = value;
        this.data = data;
        this.text = text;
        this.contact = contact;
        arrays = array;
    }

    public boolean isValue() {
        return value;
    }

    public int getData() {
        return data;
    }

    public String getText() {
        return text;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getArrays() {
        return arrays;
    }

    public JsonExample() {
    }

    @Override
    public String toString() {
        return "JsonExample{" + "value=" + value + ", data=" + data + ", text='" + text + '\'' + ", contact=" + contact
                + ", array=" + Arrays.toString(arrays) + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        JSONArray jsonArrays = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final JsonExample example = new JsonExample(false, 30, "someText", new Contact("11-111"),
                new String[] {"one", "two", "three"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("boolean", example.isValue());
        jsonObject.put("data", example.getData());
        jsonObject.put("text", example.getText());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("arrays", jsonArrays);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект в json-строку */
        System.out.println(new JSONObject(example).toString());

        /*JsonExample example = new JsonExample(true, 2021, "Thursday",
                new Contact("1122"), new String[] {"one", "two", "three"});
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(JsonExample.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(example, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        //  создаем десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            JsonExample result = (JsonExample) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }*/

    }
}
