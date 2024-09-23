package counter
import chisel3._
import _root_.circt.stage.ChiselStage


class UpCounter(counterWidth: Int) extends Module {
  val io = IO(new Bundle {
    val count = Output(UInt(counterWidth.W))
  })

  val count = RegInit(0.U(counterWidth.W))
  count := count + 1.U
  io.count := count
}


object Main extends App {
    ChiselStage.emitSystemVerilogFile(
    new UpCounter(4),
    firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info", "--split-verilog", "-o", "hdl/")
  )
}