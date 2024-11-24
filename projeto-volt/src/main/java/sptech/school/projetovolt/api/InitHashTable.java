package sptech.school.projetovolt.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sptech.school.projetovolt.service.hashtable.HashTableService;

@Controller
@RequestMapping("internal")
public class InitHashTable {
    private final HashTableService hashTableService;

    public InitHashTable(HashTableService hashTableService) {
        this.hashTableService = hashTableService;
        init();
    }

    public void init() {
        hashTableService.temp();
        hashTableService.exibir();
    }
}
