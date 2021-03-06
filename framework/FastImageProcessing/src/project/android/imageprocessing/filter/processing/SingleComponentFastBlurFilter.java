package project.android.imageprocessing.filter.processing;

import project.android.imageprocessing.filter.TwoPassMultiPixelFilter;

/**
 * A modification of the {@link FastBlurFilter} to only operate on the red component
 * @author Chris Batt
 */
public class SingleComponentFastBlurFilter extends TwoPassMultiPixelFilter {
		
	@Override
	protected String getFragmentShader() {
		return 
				 "precision mediump float;\n" 
				+"uniform sampler2D "+UNIFORM_TEXTURE0+";\n"  
				+"varying vec2 "+VARYING_TEXCOORD+";\n"	
				+"uniform float "+UNIFORM_TEXELWIDTH+";\n"
				+"uniform float "+UNIFORM_TEXELHEIGHT+";\n"
						
				
		  		+"void main(){\n"
		  		+"   vec2 firstOffset = vec2(1.3846153846 * "+UNIFORM_TEXELWIDTH+", 1.3846153846 * "+UNIFORM_TEXELHEIGHT+");\n"
		  		+"   vec2 secondOffset = vec2(3.2307692308 * "+UNIFORM_TEXELWIDTH+", 3.2307692308 * "+UNIFORM_TEXELHEIGHT+");\n"
				+"   float sum = 0.0;\n"
		  		+"   vec4 color = texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+");\n"
				+"   sum += color.r * 0.2270270270;\n"
				+"   sum += texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" - firstOffset).r * 0.3162162162;\n"
				+"   sum += texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" + firstOffset).r * 0.3162162162;\n"
				+"   sum += texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" - secondOffset).r * 0.0702702703;\n"
				+"   sum += texture2D("+UNIFORM_TEXTURE0+", "+VARYING_TEXCOORD+" + secondOffset).r * 0.0702702703;\n"
		  		+"   gl_FragColor = vec4(vec3(sum), color.a);\n"
		  		+"}\n";
	}
}
