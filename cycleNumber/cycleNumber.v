`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    09:46:31 04/04/2015 
// Design Name: 
// Module Name:    cycleNumber 
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
module cycleNumber(PCOut, CycleNumber);
	input [31:0]	PCOut;
	output	[31:0]	CycleNumber;

	assign CycleNumber = (PCOut / 4) + 1;
	

endmodule
