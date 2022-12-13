jumpBlocks.foreach((block)=>{
	world.drawElipse(block.position(),metadata.blockColor[0], metadata.getValue("circleSize"))
})

jumpBlocks.foreach((block)=>{
    world.drawElipse(block.position(), Block.AIR, metadata.getValue("circleSize")-2)
})