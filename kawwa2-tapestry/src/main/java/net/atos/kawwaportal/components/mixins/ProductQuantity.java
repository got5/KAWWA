package net.atos.kawwaportal.components.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * <p>
 * 		This Mixin is used to call the JQuery "uppydower" function to the component,
 * 		this function will add the buttons "-" and "+" to the input called to decrease and increase its value.
 * 		<br/>
 * 		To use it, call the mixin with 't:mixins="kawwa/productquantity"'.
 * </p>
 * 
 * <p>
 * 		To use the options of the "uppydowner" functions, 
 * 		add 't:options="{}"' to the input and insert the options you want to override in JSON format:
 * 
 * 		<ul>
 * 			<li>classModifier: '&lt;&lt;!&gt;&gt;' ==> Replace &lt;&lt;!&gt;&gt; by the name wanted to modify the class name of the special 
 * 				uppydowner inputs.</li>
 * 			<li>upButton: '&lt;&lt;!&gt;&gt;' ==> Replace &lt;&lt;!&gt;&gt; by the symbol wanted to render for the "up" button.</li>
 * 			<li>upPlaceBefore: &lt;&lt;!&gt;&gt; ==> Replace &lt;&lt;!&gt;&gt; by true to place the "up" button before the input.</li>
 * 			<li>downButton: '&lt;&lt;!&gt;&gt;' ==> Replace &lt;&lt;!&gt;&gt; by the symbol wanted to render for the "down" button.</li>
 * 			<li>downPlaceBefore: &lt;&lt;!&gt;&gt; ==> Replace &lt;&lt;!&gt;&gt; by false to place the "down" button after the input.</li>
 * 			<li>step: &lt;&lt;!&gt;&gt; ==> Replace &lt;&lt;!&gt;&gt; by the step value wanted to increase and decrease the input value.</li>
 * 			<li>minValue: &lt;&lt;!&gt;&gt; ==> Replace &lt;&lt;!&gt;&gt; by the minimum value wanted for the input</li>
 * 			<li>maxValue: &lt;&lt;!&gt;&gt; ==> Replace &lt;&lt;!&gt;&gt; by the maximum value wanted for the input</li>
 * 		</ul>
 * </p>
 * 
 * @tapestrydoc
 * @component_version 1.3
 */

@MixinAfter
public class ProductQuantity {

	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private JSONObject options;

	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@InjectContainer
	private TextField field;

	@SetupRender
	public void setupRender(MarkupWriter writer) {
		writer.element("p", "class", "k-quantity");
	}
	
	@BeginRender
	public void beginRender(MarkupWriter writer) {
		writer.getElement().addClassName("uppydowner");

	}

	@AfterRender
	public void afterRender(MarkupWriter writer) {
		writer.end();
		
		String id = field.getClientId();
		
		if(options == null) options = new JSONObject();
	    JSONObject opt = new JSONObject();
	    opt.put("id", id);
	    opt.put("params", options);

        javaScriptSupport.require("kawwa/productquantity").with(opt);

	}

}