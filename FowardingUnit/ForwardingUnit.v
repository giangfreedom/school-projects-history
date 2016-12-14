`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    10:08:11 04/04/2015 
// Design Name: 
// Module Name:    ForwardingUnit 
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
module ForwardingUnit(PR3RegWrite, PR4RegWrite,RS, RT, RDfromPR3, RDfromPR4,
ForwardA, ForwardB);

input PR3RegWrite;
input PR4RegWrite;

// come from id/ex PR2 stage
input	[4:0]	RS;
input	[4:0]	RT;

// come from PR3 stage
input	[4:0]	RDfromPR3;
// come from PR4 stage
input	[4:0]	RDfromPR4;

output	reg [1:0]	ForwardA;
output	reg [1:0]	ForwardB;


//If the previous instruction is going to write to the register fi le, and the write
//register number matches the read register number of ALU inputs A or B, provided
//it is not register 0, then steer the multiplexor to pick the value instead from the
//pipeline register EX/MEM.
// if (PR3RegWrite == 1 && RDfromPR3 == RS) ForwardA <= 2'b10;
always@(PR3RegWrite) begin
if ((PR3RegWrite == 1) && (RDfromPR3 == RS)) ForwardA <= 10;
if ((PR3RegWrite != 1) || (RDfromPR3 != RS)) ForwardA <= 00;

if ((PR3RegWrite == 1) && (RDfromPR3 == RT)) ForwardB <= 10;
if ((PR3RegWrite != 1) || (RDfromPR3 != RT)) ForwardB <= 00;
end

always@(PR4RegWrite) begin
if ((PR4RegWrite == 1) && (RDfromPR4 == RS)) ForwardA <= 01;
if ((PR4RegWrite != 1) || (RDfromPR4 != RS)) ForwardA <= 00;

if ((PR4RegWrite == 1) && (RDfromPR4 == RT)) ForwardB <= 01;
if ((PR4RegWrite != 1) || (RDfromPR4 != RT)) ForwardB <= 00;
end


//addagdag
endmodule
