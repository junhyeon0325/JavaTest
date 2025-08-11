package co.yedam.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Control;
import co.yedam.service.ProductService;
import co.yedam.service.ProductServiceImpl;
import co.yedam.vo.ProductVO;

public class ProductControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String prdCode = req.getParameter("prdCode");
		
		ProductService svc = new ProductServiceImpl();
		ProductVO product = svc.searchProduct(prdCode);
		List<ProductVO> productList = svc.productList();
		System.out.println(product);
		productList.sort((p1, p2) -> Integer.compare(p2.getStarPoint(), p1.getStarPoint()));

		req.setAttribute("product_info", product);
		req.setAttribute("product_list", productList);

		req.getRequestDispatcher("product/productInfo.tiles").forward(req, resp);
	}

}
