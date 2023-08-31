package project.spring.ServiceImpl;


import project.spring.myService.MessageService;

public class MessageServiceImpl implements MessageService {

    @Override
    public String sendMessages() {
        return "hello world";
    }
}
