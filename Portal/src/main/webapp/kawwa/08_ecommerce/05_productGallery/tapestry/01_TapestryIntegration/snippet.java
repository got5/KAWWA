package net.atos.kawwaportal.components.test.pages;

import java.util.ArrayList;
import java.util.List;

import net.atos.kawwaportal.components.data.ecommerce.FrontProductImage;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;

public class ProductGallery {

	@Inject
	private AssetSource as;
	
	public List<FrontProductImage> getProductImages() {
		List<FrontProductImage> list = new ArrayList<FrontProductImage>();

		list.add(new FrontProductImage() {

			public String getUriThumb() {
				return as.getContextAsset("static/img/productgallery/p1_thumb_1.jpg", null).toClientURL();
			}

			public String getUriSmall() {
				return as.getContextAsset("static/img/productgallery/p1_1.jpg", null).toClientURL();
			}

			public String getUriLarge() {
				return as.getContextAsset("static/img/productgallery/p1_hd_1.jpg", null).toClientURL();
			}

			public String getName() {
				return "Image1";
			}
		});

		list.add(new FrontProductImage() {

			public String getUriThumb() {
				return as.getContextAsset("static/img/productgallery/p1_thumb_2.jpg", null).toClientURL();
			}

			public String getUriSmall() {
				return as.getContextAsset("static/img/productgallery/p1_2.jpg", null).toClientURL();
			}

			public String getUriLarge() {
				return as.getContextAsset("static/img/productgallery/p1_hd_2.jpg", null).toClientURL();
			}

			public String getName() {
				return "Image2";
			}
		});

		return list;
	}

}
