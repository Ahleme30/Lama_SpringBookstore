package com.vermeg.ams.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.cfg.annotations.ListBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vermeg.ams.entities.Book;
import com.vermeg.ams.entities.User;
import com.vermeg.ams.entities.Order;
import com.vermeg.ams.entities.OrderDetails;
import com.vermeg.ams.repositories.BookRepository;
import com.vermeg.ams.repositories.UserRepository;
import com.vermeg.ams.repositories.OrderRepository;

@Controller
@RequestMapping("/order/")
public class OrderController {

	private OrderRepository orderRepository;
	private UserRepository userRepository;
	private BookRepository bookrepository;

	@Autowired
	public OrderController(OrderRepository orderRepository, UserRepository userRepository,
			BookRepository bookrepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.bookrepository = bookrepository;
	}

	@GetMapping("list")
	// @ResponseBody
	public String listorders(Model model) {
		List<Order> com = (List<Order>) orderRepository.findAll();
		if (com.size() == 0)
			com = null;
		model.addAttribute("orders", com);
		return "order/listOrders";
		// System.out.println(lp);
		// return "Nombre de fournisseur = " + lp.size();
	}

	@GetMapping("add")
	public String showAddorderForm(Model model) {
		Order order = new Order();// object dont la valeur des attributs par defaut
		LocalDate lt = LocalDate.now();
		order.setOrderDate(lt);
		model.addAttribute("order", order);
		List<User> cl = (List<User>) userRepository.findAll();
		model.addAttribute("clients", cl);
		List<Book> lb = (List<Book>) bookrepository.findAll();
		model.addAttribute("books", lb);
		return "order/addorder";
	}
	
	@PostMapping("add")
	// @ResponseBody
	
	
	public String addOrder(@Valid Order order, @RequestParam("checkid") List<Integer> listids,
			@RequestParam("idclient") int idUser) {
		double prices =0.0;
		User user = userRepository.findById(idUser)
				.orElseThrow(() -> new IllegalArgumentException("invalid user"));
	//	order.setUser_u(user);
		List<OrderDetails> lisb= new ArrayList<OrderDetails>();
	
		for (int i = 0; i < listids.size(); i++) {
			OrderDetails ord=new OrderDetails();
			
			Book b = bookrepository.findById(listids.get(i))
					.orElseThrow(() -> new IllegalArgumentException("invalid id "));
			ord.setBook(b);
			ord.setIdLC(idUser);
			ord.setPrixUnitaire(b.getPrice());
			ord.setQuantiteeCommandee(1);
			prices+=b.getPrice();
			lisb.add(ord);
			}
	//	List<OrderDetails> setlisb = new HashSet<OrderDetails>(lisb);
		System.out.print("**********************molka");
		System.out.print(lisb.get(0).getPrixUnitaire());
		System.out.print(lisb.get(1).toString());
		//System.out.print(setlisb.getClass().toString());
		
		order.setOrderDetails(lisb);
		order.setPrice(prices);
		orderRepository.save(order);
		return "redirect:list";
	}
	
	@GetMapping("showdetails/{id}")
	public String showdetails(@PathVariable("id")int id,Model m) {
		
		Order o = orderRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Invalid ID"+id));
	//	List<Book> mybooks = o.getmybooks();
		System.out.println("------------------------------------------------------------------------");
		//System.out.println(mybooks.size()+mybooks.toString());
		System.out.println("**************************************************************************");
	//	m.addAttribute("mybooks", mybooks);
		return"order/details";
	}
	
	
	@GetMapping("delete/{id}")
	public String deleteorder(@PathVariable("id") int id, Model model) {
		
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
		
		orderRepository.delete(order);
		
		return "redirect:../list";
	}

	@GetMapping("edit/{id}")
	public String showorderFormToUpdate(@PathVariable("id") int id, Model model) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));

		model.addAttribute("order", order);
		return "order/updateorder";
	}

	@PostMapping("update")
	public String updateorder(@Valid Order order, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "order/updateorder";
		}
		orderRepository.save(order);
		return "redirect:list";
	} 
	
}
