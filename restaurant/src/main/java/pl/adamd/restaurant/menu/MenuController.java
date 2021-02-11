package pl.adamd.restaurant.menu;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/menu")
@CrossOrigin("http://localhost:4200")
public class MenuController {


    private MenuRepository menuRepository;

    public MenuController(MenuRepository dishRepository) {
        this.menuRepository = dishRepository;
    }

    @GetMapping("/get-all")
    public List<Menu> getAllMenuItem() {
        return menuRepository.findAll();
    }

    @GetMapping("/by-name/{name}")
    public List<Menu> findMenuItemByName(@PathVariable String name){
        return menuRepository.findByName(name);
    }

    @PostMapping
    public Menu addNewMenuItem(@RequestBody Menu menuItem){
        return menuRepository.save(menuItem);
    }

}

