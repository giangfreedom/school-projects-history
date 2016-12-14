`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    12:54:18 04/04/2015 
// Design Name: 
// Module Name:    HazardDetector 
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
module HazardDetector(Rs2, Rt2, Rd4, Rt4, Rd3, ALUOp2, ALUOp4, ALUOp3, HAs2d4, HAs2t4, HBt2d4, HBt2t4, HAs2d3, HBt2d3);
	input [4:0] Rs2;
	input [4:0] Rt2;
	
	input [4:0] Rd4;
	input [4:0] Rt4;
	input [4:0] Rd3;
	
	input [1:0]	ALUOp2;
	input [1:0]	ALUOp4;
	input [1:0]	ALUOp3;
	
	output reg HAs2d4;
	output reg HAs2t4;
	
	output reg HBt2d4;
	output reg HBt2t4;
	
	output reg HAs2d3;
	output reg HBt2d3;
	
	initial begin
	HAs2d4 = 0;
	HAs2t4 = 0;
	HAs2d3 = 0;
	
	HBt2d4 = 0;
	HBt2t4 = 0;
	HBt2d3 = 0;
	end
	
	always @* begin 
		// LD follow up by ALU
		if((ALUOp2 == 2'b10) && (ALUOp4 == 2'b00)) begin 	
			// des of LD stage 4 = input rs of ALU stage 2
			if(Rs2 == Rt4) begin
				HAs2t4 <= 1;
			end
			if(Rs2 != Rt4) begin
				HAs2t4 <= 0;
			end
			
			// des of LD stage 4 = input rt of ALU stage 2
			if(Rt2 == Rt4) begin
				HBt2t4 <= 1;
			end
			if(Rt2 != Rt4) begin
				HBt2t4 <= 0;
			end
		end
		
		// alu follow up by alu
		if((ALUOp2 == 2'b10) && (ALUOp4 == 2'b10)) begin
			// des of ALU stage 4 = input rs of ALU stage 2
			if(Rs2 == Rd4) begin
				HAs2d4 <= 1;
			end
			if(Rs2 != Rd4) begin
				HAs2d4 <= 0;
			end
			
			// des of ALU stage 4 = input rt of ALU stage 2
			if(Rt2 == Rd4) begin
				HBt2d4 <= 1;
			end
			if(Rt2 != Rd4) begin
				HBt2d4 <= 0;
			end
		end
		
		// alu follow up by alu with no gap
		if((ALUOp2 == 2'b10) && (ALUOp3 == 2'b10)) begin
			if(Rs2 == Rd3) begin
				HAs2d3 <= 1;
			end
			if(Rs2 != Rd3) begin
				HAs2d3 <= 0;
			end
	
			if(Rt2 == Rd3) begin
				HBt2d3 <= 1;
			end
			if(Rt2 != Rd3) begin
				HBt2d3 <= 0;
			end
		end
		
	end
	
endmodule
