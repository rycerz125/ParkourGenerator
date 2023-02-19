jumpBlocks.foreach((block)=>{
	world.drawElipse(block.position(), TerrainColor.MAIN,  metadata.getValue("circleSize"))
})

jumpBlocks.foreach((block)=>{
    world.drawElipse(block.position(), TerrainColor.AIR, metadata.getValue("circleSize")-2)
})