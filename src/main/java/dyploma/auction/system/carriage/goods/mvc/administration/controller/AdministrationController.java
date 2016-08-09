package dyploma.auction.system.carriage.goods.mvc.administration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dyploma.auction.system.carriage.goods.mvc.administration.dao.AdministrationDAOInterface;
import dyploma.auction.system.carriage.goods.mvc.administration.model.TypeOfBody;

@Controller
public class AdministrationController {

	@Autowired()
	private AdministrationDAOInterface dao;
	
	@RequestMapping("/")
	public ModelAndView index()
	{
		ModelAndView modelAndView = new ModelAndView("index");
		List<TypeOfBody> list = dao.getTypeOfBodies();
		modelAndView.addObject("list", list);
		return modelAndView;
	}
	
	@RequestMapping("/test")
	public ModelAndView test()
	{
		ModelAndView modelAndView = new ModelAndView("test");
		List<TypeOfBody> list = dao.getTypeOfBodies();
		modelAndView.addObject("list", list);
		return modelAndView;
	}
}
