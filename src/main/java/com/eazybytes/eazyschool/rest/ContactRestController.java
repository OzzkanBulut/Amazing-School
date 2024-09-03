package com.eazybytes.eazyschool.rest;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.model.Response;
import com.eazybytes.eazyschool.repository.ContactRepository;
import com.eazybytes.eazyschool.utils.constant.EazySchoolConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController // = @Controller + @ResponseBody, therefore, @ResponseBody is no longer needed
//@Controller
// Allow both xml and json
@RequestMapping(value = "/api/contact",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins = "*") // Allow any domain
public class ContactRestController {

    @Autowired
    ContactRepository contactRepository;


    @GetMapping("/getMessagesByStatus")
//    @ResponseBody // Do not expect any view
    public List<Contact> getMessagesByStatus(@RequestParam(name = "status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMsgsByStatus")
//    @ResponseBody
    public List<Contact> getAllMsgsByStatus(@RequestBody Contact contact) {

        if (null != contact && null != contact.getStatus()) {
            return contactRepository.findByStatus(contact.getStatus());
        }
        return List.of();

    }

    @PostMapping("/saveMsg")
//    @ResponseBody
    public ResponseEntity<Response> saveMsg(@RequestHeader("invocationFrom")String invocationFrom,
                                            @RequestBody Contact contact){
        log.info(String.format("Header invocationFrom = %s",invocationFrom));
        contactRepository.save(contact);

        Response response = new Response("201","Saved successfully!");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgSaved","true")
                .body(response);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> contactRequestEntity){

        HttpHeaders headers = contactRequestEntity.getHeaders();
        headers.forEach((key,value) -> {
            log.info(String.format(
                    "Header '%s' = %s",key,value.stream().collect(Collectors.joining("|"))));
        });

        Contact contact = contactRequestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());

        Response response = new Response("200","Deleted Successfully!");

        return ResponseEntity
                .status                                                                                                                                                           (HttpStatus.OK)
                .body(response);


    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq){
        Response response = new Response();
        Optional<Contact> contact = contactRepository.findById(contactReq.getContactId());

        if(contact.isPresent()){
            contact.get().setStatus(EazySchoolConstants.CLOSE);
            contactRepository.save(contact.get());
        }else{
            response.setStatusCode("400");
            response.setStatusMsg("Invalid Contact Id received");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        response.setStatusCode("200");
        response.setStatusMsg("Message successfully closed");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
