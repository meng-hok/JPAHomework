package com.example.getStartedExercise.getstartedexercise.controller.ArticleController;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.example.getStartedExercise.getstartedexercise.configuration.FileConguration;
import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository.ArticleRepoDB;
import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository.CategoryRepository;
import com.example.getStartedExercise.getstartedexercise.repository.model.Book;
import com.example.getStartedExercise.getstartedexercise.repository.model.Category;
import com.example.getStartedExercise.getstartedexercise.repository.provider.MyProvider;
import com.example.getStartedExercise.getstartedexercise.service.ArticleServiceImp;
import com.example.getStartedExercise.getstartedexercise.service.ArticleService.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
/**
 * ArticleController
 */
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Transactional
public class ArticleController {

    ArticleServiceImp articleServiceImp;
    static String lang  = "en";
    private List<Book> list;
    Boolean search_via_all = true;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    public ArticleRepoDB articleRepoDB;
   
    @Autowired
    ArticleController(ArticleService articleService){
        this.articleServiceImp = articleService;
    }

    @RequestMapping(value={"", "/", "home"})
    public String display(ModelMap modelMap) {
        
       modelMap.addAttribute("ARTICLES", articleServiceImp.findAll());
        modelMap.addAttribute("VAR", 10);
        search_via_all = true;
        return "redirect:/paginator?limit=10&page=1";
    }
    

    @RequestMapping("/add")
    public String add(ModelMap modelMap){
        modelMap.addAttribute("ARTICLE", new Book());
        modelMap.addAttribute("CATEGORIES",categoryRepository.findAll());
        modelMap.addAttribute("category",new Category());
        return "form";
    }
    //,@ModelAttribute("category") Category category
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("ARTICLE") Book article ,BindingResult bindingResult,@RequestParam("files") MultipartFile files,ModelMap modelMap){
        if(bindingResult.hasErrors()){
          modelMap.addAttribute("ARTICLE", article);
          modelMap.addAttribute("CATEGORIES",categoryRepository.findAll());
          modelMap.addAttribute("category",new Category());
       
          return "form";
       }
        if(files.isEmpty()){

       }else{
            article.setThumbnail(FileConguration.uploaded(files)); 
       }
        articleServiceImp.add(article);
        return "redirect:/home";
    }

    @RequestMapping("/article/{id}") 
    public String ArticleDisplay (@PathVariable("id") int id,ModelMap modelMap){
        modelMap.addAttribute("ARTICLE", articleServiceImp.find(id));
        return "upload";
    }


    //only has in table
    @RequestMapping("/edit/author")
    public String edit(@RequestParam("id") int id,ModelMap modelMap ) {
       
        modelMap.addAttribute("ARTICLE", articleServiceImp.find(id));
        modelMap.addAttribute("INDEX",id);
        modelMap.addAttribute("CATEGORIES",categoryRepository.findAll());
        modelMap.addAttribute("category",new Category());
      
        return "form";
    }

    @PostMapping("/edit/author")
    public String edit(@Valid @ModelAttribute("ARTICLE") Book article,BindingResult bindingResult,@RequestParam("files") MultipartFile file ,ModelMap modelMap ){
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("ARTICLE",article);
            modelMap.addAttribute("INDEX",article.getId());
            modelMap.addAttribute("CATEGORIES",categoryRepository.findAll());
            modelMap.addAttribute("category",new Category());
          
             return "form";
         }
        if(file.isEmpty()){
         
        }else{
             article.setThumbnail(FileConguration.uploaded(file)); 
            
        }
        System.out.println(article.getThumbnail());
        articleServiceImp.update(article); 
        return "redirect:/home";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam int id ){
        articleServiceImp.delete(id);
        return "redirect:/home";
    }
   
 

    @RequestMapping("/faker")
    String faker()
    {
        //fakeData();
        articleRepoDB.add(new Book());
        return "redirect:/home";
    }

   



     @PostMapping(value = "/search")
     String search(@RequestParam("search_title") String title, @RequestParam("search_type") int type ){
        
        search_via_all = false;
        this.list = findByName(title, type);
        return "redirect:/paginator?limit=10&page=1";
     }

     @GetMapping(value="paginator")
     public String postMethodName(@RequestParam("limit") int limit , @RequestParam("page") int page ,ModelMap modelMap) {
            if(search_via_all == true ){
                list =  articleServiceImp.findAll();
            }
                
            List<Book> newList = new ArrayList<>();
            for (int i = limit * (page-1) ; i < limit * page; i++) {
                try {
                    newList.add(list.get(i));
                } catch (Exception e) {

                    break;
                }
                
            }
         modelMap.addAttribute("CATEGORIES",categoryRepository.findAll());   
         modelMap.addAttribute("ARTICLES", newList);
         int paginationAmount = (list.size() / 10 ) +  ((list.size() % 10) > 0 ? 1 :0  ) ;
         modelMap.addAttribute("PAGEAMOUNT",paginationAmount);
         modelMap.addAttribute("CURRENTPAGE",page);
         return "home";
     }
     List<Book> fakeData( ){
        for (int i = 0; i < 20; i++) {
            Book article = new Book();
            article.setId(50);
            article.setTitle("hahahaha");
            add(article);
        }
        return null;
    }


    List<Book> findByName(String title,int id) {
        String searchTitle = '%'+title+'%';
        if(id == 0){
            return articleRepoDB.findByTitle(searchTitle);
        }
        return articleRepoDB.findByTitleAndType(searchTitle,id);
     }  

     public String add(@ModelAttribute Book article ){
        articleServiceImp.add(article);
          return "redirect:/home";
      }

      @GetMapping("/translate")
      String translate() {
          return "backer/backer";
      }

      
 
}