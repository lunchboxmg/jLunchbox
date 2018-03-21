#version 330

layout(location = 0) in vec3 in_position;
layout(location = 1) in vec3 in_normal;
layout(location = 2) in vec3 in_color;

out vec3 pass_color;

uniform mat4 matView;
uniform mat4 matProj;



void main(void) {

	vec4 worlPosition = vec4(in_position, 1.0);
	vec4 eyeCoords = matView * worldPosition;
	gl_position = matProj * eyeCoords;
	
	pass_color = in_color;

	
}