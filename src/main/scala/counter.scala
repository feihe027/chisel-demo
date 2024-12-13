package counter
import chisel3._
import _root_.circt.stage.ChiselStage
import chisel3.util._  


class UpCounter(counterWidth: Int) extends Module {
  val io = IO(new Bundle {
    val count = Output(UInt(counterWidth.W))
  })

  val count = RegInit(0.U(counterWidth.W))
  count := count + 1.U
  io.count := count
  printf(cf"count:${count}")
}

object Main extends App {
  emitVerilog(
    new UpCounter(4),
    Array(
      "--emission-options=disableMemRandomization,disableRegisterRandomization",
      "--emit-modules=verilog", 
      "--info-mode=use",
      "--target-dir=hdl",
      "--full-stacktrace",
      // "--help"
    )
  )
}