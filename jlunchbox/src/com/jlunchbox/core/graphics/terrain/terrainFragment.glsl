#version 330

in vec3 pass_color;

void main(void) {

	out_color = vec4(pass_color, 1.0);
}

