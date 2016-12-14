`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:45:37 03/07/2015 
// Design Name: 
// Module Name:    Adder2 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
module Adder2(Adder1Out, ShiftLeftOut, Adder2Out);
	input[31:0]  Adder1Out;
	input [31:0] ShiftLeftOut;
	output [31:0] Adder2Out;
	
	assign Adder2Out = Adder1Out + ShiftLeftOut;

endmodule
