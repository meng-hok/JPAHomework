package com.example.getStartedExercise.getstartedexercise.controller.ArticleController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.getStartedExercise.getstartedexercise.configuration.FileConguration;
import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository.ArticleRepoDB;
import com.example.getStartedExercise.getstartedexercise.repository.ArticleRepository.CategoryRepository;
import com.example.getStartedExercise.getstartedexercise.repository.model.Article;
import com.example.getStartedExercise.getstartedexercise.repository.model.Category;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
/**
 * ArticleController
 */
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ArticleController {

    ArticleServiceImp articleServiceImp;
    static String lang  = "en";
   
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
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/add")
    public String add(ModelMap modelMap){
        modelMap.addAttribute("ARTICLE", new Article());
        modelMap.addAttribute("CATEGORIES",categoryRepository.findAll());
        modelMap.addAttribute("category",new Category());
        return "form";
    }
    //,@ModelAttribute("category") Category category
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Article article ,BindingResult bindingResult,@RequestParam("files") MultipartFile files,ModelMap modelMap){
        if(bindingResult.hasErrors()){
          modelMap.addAttribute("ARTICLE", new Article());
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
    public String edit(@ModelAttribute Article article,@RequestParam("files") MultipartFile file ){
        if(file.isEmpty()){
         
        }else{
             article.setThumbnail(FileConguration.uploaded(file)); 
            
        }
        articleServiceImp.update(article); 
        return "redirect:/home";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam int id ){
        articleServiceImp.delete(id);
        return "redirect:/home";
    }
   
    @Autowired
    public ArticleRepoDB articleRepoDB;

    @RequestMapping("/faker")
    String faker()
    {
        //fakeData();
        articleRepoDB.add(new Article());
        return "redirect:/home";
    }

    List<Article> fakeData( ){
        // List<Article> list =new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Article article = new Article();
            article.setId(50);
            article.setTitle("hahahaha");
            add(article);
        }
        return null;
    }

    public String add(@ModelAttribute Article article ){
       articleServiceImp.add(article);
         return "redirect:/home";
     }

     @PostMapping(value = "/search")
     String search(@RequestParam("search_title") String title, @RequestParam("search_type") int type ){
        
        search_via_all = false;
        this.list = findByName(title, type);
        return "redirect:/paginator?limit=10&page=1";
     }

     List<Article> findByName(String title,int id) {
        String searchTitle = '%'+title+'%';
        if(id == 0){
            return articleRepoDB.findByTitle(searchTitle);
        }
        return articleRepoDB.findByTitleAndType(searchTitle,id);
     }  

     private List<Article> list;
     Boolean search_via_all = true;
     @GetMapping(value="paginator")
     public String postMethodName(@RequestParam("limit") int limit , @RequestParam("page") int page ,ModelMap modelMap) {
            if(search_via_all == true ){
                list =  articleServiceImp.findAll();
            }
                
            List<Article> newList = new ArrayList<>();
            for (int i = limit * (page-1) ; i < limit * page; i++) {
                try {
                    newList.add(list.get(i));
                } catch (Exception e) {

                    break;
                }
                
            }
         modelMap.addAttribute("CATEGORIES",categoryRepository.findAll());   
         modelMap.addAttribute("ARTICLES", newList);
        //  System.out.println(newList);
         int paginationAmount = (list.size() / 10 ) +  ((list.size() % 10) > 0 ? 1 :0  ) ;
         modelMap.addAttribute("PAGEAMOUNT",paginationAmount);
         modelMap.addAttribute("CURRENTPAGE",page);
         return "home";
     }

   
    
}