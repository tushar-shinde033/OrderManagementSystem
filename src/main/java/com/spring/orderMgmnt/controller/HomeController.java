package com.spring.orderMgmnt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.orderMgmnt.entities.Customer;
import com.spring.orderMgmnt.entities.Order;
import com.spring.orderMgmnt.helper.Message;
import com.spring.orderMgmnt.service.AdminService;
import com.spring.orderMgmnt.service.CommunicationService;
import com.spring.orderMgmnt.service.CustomerService;
import com.spring.orderMgmnt.service.OrderService;
import com.spring.orderMgmnt.service.ProductService;

import jakarta.servlet.http.HttpSession;
import com.spring.orderMgmnt.entities.Communication;
import com.spring.orderMgmnt.entities.Product;

@Controller
public class HomeController {
	
	@Autowired
	public CustomerService customerService;
	
	@Autowired
	public OrderService orderService;
	
	@Autowired
	public CommunicationService communicationService;
	
	@Autowired
	public ProductService productService;
	
	@Autowired
	public AdminService adminService;
	
	@RequestMapping("/")
	public String getHome() {
		return "MainAdminLogin";
//		return "Button";
	}
	
	@RequestMapping("/customer")
	public String customerManagement() {
		return "CustomerHome";
	}
	@RequestMapping("/adminlogin")
	public String Login() {
		return "AdminLogin";
	}

	@RequestMapping("/newcustomer")
	public String signup() {
		return "AddCust";
	}
	@RequestMapping("/updatecustomer")
	public String uCust() {
		return "UpdateCust";
	}
	@RequestMapping("/deletecustomer")
	public String dCust() {
		return "DeleteCust";
	}
	@RequestMapping("/getcustomer")
	public String gCust() {
		return "GetCust";
	}
	
	@PostMapping("/main-admin-login")
	public String addMainAdmin(@Valid @RequestParam(value="agreement",defaultValue = "false") boolean agreement,@Valid @RequestParam("email")String email,@Valid @RequestParam("password")String password, Model model) {	
		
		String result=adminService.findByEmailAndPassword(email, password);
		try {
			if(!agreement) {
				throw new Exception("You have Not Agreed Terms and Conditions");
			}
			
			if(result.equals("EXIST")) {
				return "Button";
			}else {
				model.addAttribute("errormessage","Email and Password are not matched !!");
				return "MainAdminLogin";
			}
		    }catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message",new Message("Something Went Wrong !!"+e.getMessage(),"alert-danger"));
			return "MainAdminLogin";
		   }
	}
	
	@PostMapping("/admin-login")
	public String addAdmin(@Valid @RequestParam(value="agreement",defaultValue = "false") boolean agreement,@Valid @RequestParam("email")String email,@Valid @RequestParam("password")String password, Model model) {	
		try {
			if(!agreement) {
				throw new Exception("You have Not Agreed Terms and Conditions");
			}
			
			if(email.equals("tusharshinde0330@gmail.com")&&password.equals("Pass@123")) {
				return "redirect:/get-admin-customer";
			}else {
				model.addAttribute("errormessage","Email and Password are not matched !!");
				return "AdminLogin";
			}
		    }catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message",new Message("Something Went Wrong !!"+e.getMessage(),"alert-danger"));
			return "AdminLogin";
		   }
	}
	
	@RequestMapping("/get-admin-customer")
	public String getAdminCustomer(Model model) {
		List<Customer> list=this.customerService.getAdminCustomers();
		model.addAttribute("customers",list);
		return "AdminCustomerData";
	}
	

	@PostMapping("/add-customer")
	public String addCustomer(@Valid @ModelAttribute("customer")Customer customer,@RequestParam(value="agreement",defaultValue = "false") boolean agreement,Model model,BindingResult bindindResult) {	
		try {
			if(!agreement) {
				throw new Exception("You have Not Agreed Terms and Conditions");
			}
			if(bindindResult.hasErrors()) {
				model.addAttribute("user",customer);
				return "AddCust";
			}
			Customer cust=this.customerService.saveCustomer(customer);
			model.addAttribute("user",new Customer()); 
		    model.addAttribute("message",new Message("Successfully Register !!","alert-success"));
			return "AddCust";
		    }catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user",customer);
			model.addAttribute("message",new Message("Something Went Wrong !!"+e.getMessage(),"alert-danger"));
			return "AddCust";
		   }
	}

	@PostMapping("/update-customer")
	public String updateCustomer(@Valid @RequestParam("emailAddr")String email,@ModelAttribute("customer")Customer customer,Model model,@RequestParam(value="agreement",defaultValue = "false") boolean agreement,BindingResult bindindResult) {
		try {
			if(!agreement) {
				throw new Exception("You have Not Agreed Terms and Conditions");
			}
			if(bindindResult.hasErrors()) {
				model.addAttribute("user",customer);
				return "UpdateCust";
			}
			String result=this.customerService.updateCustomer(email, customer);
			if(result.equals("FIND")) {
				model.addAttribute("user",new Customer()); 
			    model.addAttribute("message",new Message("Successfully Update Customer !!","alert-success"));
				return "UpdateCust";
			}else {
				model.addAttribute("updatecustomerdata","Error:The Customer Email Address Not Found to Update The Data...");
				return "UpdateCust";	
			}	
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user",customer);
			model.addAttribute("message",new Message("Something Went Wrong !!"+e.getMessage(),"alert-danger"));
			return "UpdateCust";
		}
	}

	@PostMapping("/delete-customer")
	public String deleteCustomer(@ModelAttribute("customer")Customer customer,Model model) {
		String email=customer.getEmail();
		String result=this.customerService.deleteCustomer(email);
		if(result.equals("EXIST"))
		{
			model.addAttribute("message",new Message("Successfully Delete Customer !!","alert-success"));
			return "DeleteCust";
		}else {
			model.addAttribute("message",new Message("Error:The Customer Email Address Not Found to Delete The Data...","alert-danger"));
			return "DeleteCust";
		}
	}
	
	@RequestMapping("/get-customer")
	public String getCustomer(Model model,String email) {
		
		Customer list=this.customerService.getCustomers(email);
		model.addAttribute("customers",list);
		System.out.println(list);
		return "AllCustomerData";
	}
	
	
//	 This is an Order Section Where all orders are managed....
	
	@RequestMapping("/order")
	public String orderManagement() {

		return "CheckCustomerExist";
	}
	
	@RequestMapping("/check-customer")
	public String checkCustomerExists(@ModelAttribute("customer")Customer customer,Model model,HttpSession session) {
		String result = customerService.registerCustomer(customer.getName(), customer.getEmail());
		if(result.equals("EXIST")) {
			String customerEmail=customer.getEmail();
			session.setAttribute("customerEmail",customerEmail);
			return "OrderHome";
		}
		else {
			model.addAttribute("checkCustomer","Enter A Valid Email This Email Is Not Existed...");
			return "CheckCustomerExist";
		}	
	}
	
	@RequestMapping("/addorder")
	public String aOrder() {
		return "AddOrder";
	}

	@RequestMapping("/updateorder")
	public String uOrder() {
		return "UpdateOrder";
	}
	
	@RequestMapping("/deleteorder")
	public String dOrder() {
		return "DeleteOrder";
	}
	
	@RequestMapping("/getcustomerorderhistory")
	public String gOrder() {
		return "CustomerOrderHistory";
	}
	
	@PostMapping("/add-order")
	public String addOrder(@Valid @ModelAttribute("order")Order order,HttpSession session,@RequestParam(value="agreement",defaultValue = "false") boolean agreement,Model model,BindingResult bindindResult) {
		try {
			if(!agreement) {
				throw new Exception("You have Not Agreed Terms and Conditions");
			}
			if(bindindResult.hasErrors()) {
				model.addAttribute("user",order);
				return "AddOrder";
			}
//			main login
			String email=(String) session.getAttribute("customerEmail");
			Order order1=this.orderService.saveOrder(email,order);
			
//			this is for validation
			model.addAttribute("user",new Customer()); 
		    model.addAttribute("message",new Message("Successfully Add Order !!","alert-success"));
			return "AddOrder";
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user",order);
			model.addAttribute("message",new Message("Something Went Wrong !!"+e.getMessage(),"alert-danger"));
			return "AddOrder";
		   }
	}
	
	@PostMapping("/update-order")
	public String updateOrder(@RequestParam("oldproductName") String oldproductName,@RequestParam("email")String email,@ModelAttribute("order")Order order,Model model) {
		String result=this.orderService.updateOrderByProductAndEmail(oldproductName, email, order);
		if(result.equals("FOUND")) {
			return "success";
		}else {
			model.addAttribute("updateOrder","Error: No old product name or email found so you cannot update the order!!");
			return "UpdateOrder";	
		}	
	}

	@PostMapping("/delete-order")
	public String deleteOrder(@RequestParam String name,@RequestParam String email,Model model) {
		String result=this.orderService.findByProductNameAndCustomer_Email(name, email);
		System.out.println(result);
		if(result.equals("EXIST")) {
			return "success";
		}
		else {
			model.addAttribute("deleteorder","Error: No order found for this product linked to Customer email so you cannot delete the order!!");
			model.addAttribute("valid","!! Please Enter Valid Data !!");
			return "DeleteOrder";
		}	
	}
	
	@GetMapping("/get-orders")
	public String getOrders(Model model,HttpSession session) {
		String email=(String) session.getAttribute("customerEmail");
		List<Order>orders=this.orderService.getOrdersByCustomerEmail(email);
		model.addAttribute("orders",orders);
		return "AllOrdersData";
	}
	
	
//	This is an Communication Section
	
	@RequestMapping("/communication")
	public String Communication() {
		return "CommunicationHome";
	}
	
	@RequestMapping("/addcommunication")
	public String addCommunication() {
		return "AddCommunication";
	}
	
	@RequestMapping("/updatecommunication")
	public String updateCommunication() {
		return "UpdateCommunication";
	}
	
	@RequestMapping("/deletecommunication")
	public String deleteCommunication() {
		return "DeleteCommunication";
	}
	
	@RequestMapping("/communicationlogin")
	public String CommunicationLogin() {
		return "CommunicationLogin";
	}
	
	@PostMapping("/add-communication")
	public String addCommunication(@Valid @ModelAttribute("communication") Communication communication,HttpSession session,@RequestParam String email,@RequestParam(value="agreement",defaultValue = "false") boolean agreement,Model model,BindingResult bindindResult) {
		try {
			if(!agreement) {
				throw new Exception("You have Not Agreed Terms and Conditions");
			}
			if(bindindResult.hasErrors()) {
				model.addAttribute("user",communication);
				return "AddCommunication";
			}
//			main login
			Communication communication1=this.communicationService.addCommunication(email, communication);
			
//			this is for validation 
		    model.addAttribute("message",new Message("Successfully Add Communication : You Will get an Email or Call From Admin","alert-success"));
			return "AddCommunication";
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user",communication);
			model.addAttribute("message",new Message("Something Went Wrong !!"+e.getMessage(),"alert-danger"));
			return "AddCommunication";
		   }
	}

	@GetMapping("/get-communication")
	public String getCommunication(Model model,@RequestParam String email) {
		List<Communication>communications=this.communicationService.getCommunicationByCustomerEmail(email);
		model.addAttribute("communications",communications);
		return "GetCommunication";
	}
	
	
	@PostMapping("/update-communication")
	public String updateCommunication(@Valid @RequestParam("oldintractiondate")String oldintractiondate,@ModelAttribute("communication")Communication communication,Model model,@RequestParam(value="agreement",defaultValue = "false") boolean agreement,BindingResult bindindResult) {
		try {
			if(!agreement) {
				throw new Exception("You have Not Agreed Terms and Conditions");
			}
			if(bindindResult.hasErrors()) {
				model.addAttribute("user",communication);
				return "UpdateCommunication";
			}
			String result=this.communicationService.updateCommunication(oldintractiondate, communication);
			System.out.println(result);
			if(result.equals("FIND")) {
				model.addAttribute("user",new Communication()); 
			    model.addAttribute("message",new Message("Successfully Update Communication !!","alert-success"));
				return "UpdateCommunication";
			}else {
				model.addAttribute("updatecommunicationdata","Error:The Date Was Not Found !!");
				return "UpdateCommunication";	
			}	
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user",communication);
			model.addAttribute("message",new Message("Something Went Wrong !!"+e.getMessage(),"alert-danger"));
			return "UpdateCommunication";
		}
	}
	
	@PostMapping("/delete-communication")
	public String deleteCommunication(@RequestParam("communicationid")long id,Model model) {
		String result=this.communicationService.deleteCommunication(id);
		if(result.equals("EXIST"))
		{
			model.addAttribute("message",new Message("Successfully Delete Comimunication !!","alert-success"));
			return "DeleteCommunication";
		}else {
			model.addAttribute("message",new Message("Error:Communication Id Not Found to Delete The Data...","alert-danger"));
			return "DeleteCommunication";
		}
	}


	
	
	
//----------------This is a Product Section----------------
	
	
	@RequestMapping("/product")
	public String Product() {
		return "ProductHome";
	}
	
	@RequestMapping("/addproduct")
	public String addProduct() {
		return "AddProduct";
	}
	
	@RequestMapping("/getproduct")
	public String getProduct() {
		return "GetProducts";
	}
	
	@PostMapping("/add-product")
	public String addProduct(@Valid @ModelAttribute("product") Product product,@RequestParam(value="agreement",defaultValue = "false") boolean agreement,Model model,BindingResult bindindResult) {
		try {
			if(!agreement) {
				throw new Exception("You have Not Agreed Terms and Conditions");
			}
			if(bindindResult.hasErrors()) {
				model.addAttribute("user",product);
				return "AddProduct";
			}
//			main login
			Product product1=this.productService.saveProduct(product);
//			this is for validation 
		    model.addAttribute("message",new Message("Successfully Add Product: ","alert-success"));
			return "AddProduct";
			
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user",product);
			model.addAttribute("message",new Message("Something Went Wrong !!"+e.getMessage(),"alert-danger"));
			return "AddProduct";
		   }
	}
	@GetMapping("/get-product")
	public String getProducts(Model model) {
		List<Product>products=this.productService.getAllProducts();
		model.addAttribute("products",products);
		return "GetProducts";
	}
	
//	this is a sales section
	
	@RequestMapping("/sales")
	public String getSales() {
		return "Sales";
	}
	
}











