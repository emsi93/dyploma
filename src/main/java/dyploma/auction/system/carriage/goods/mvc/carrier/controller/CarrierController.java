package dyploma.auction.system.carriage.goods.mvc.carrier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarrierController {

	@RequestMapping("/")
	public ModelAndView index()
	{
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("message", "siema");
		return modelAndView;
	}
}
