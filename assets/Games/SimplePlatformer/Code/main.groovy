import leikr.Engine
class SimplePlatformer extends Engine {
	
	def p = [:]
	def i = [:]
	def bc = [:]
	def sc = [:]
	
	int height = 15
		
	Random rand
	void move(){
		def play = false
		p.vx = 0
		if(key("Left")){		
			p.vx =-1
			i.x += 0.5
			p.f = 0
		}
		if(key("Right")){
			p.vx = 1
			i.x -= 0.5
			p.f = 1
		}
		if( solid(p.x,p.y+8+p.vy) || solid(p.x+7,p.y+8+p.vy) ){
        	p.vy=0
		}else{
		    p.vy = p.vy+0.2
		}
		
		if(key("Up") && p.vy==0){
			p.vy=-2.5
			play = true
		}	
		
		if( p.vy<0 && (solid(p.x+p.vx,p.y+p.vy) || solid(p.x+7+p.vx,p.y+p.vy)) ){
		    p.vy=0
		    play = false
		}   
		
		if(play) sfx("jump")

		p.x=p.x+p.vx
		p.y=p.y+p.vy		
	}
	
	def solid(x,y){
		float mx = (x)/8 
		float my = (y)/8
		if(mapCellId(mx,my)	 > 0){
			return true
		}
		return false
	}
	
	void create(){		
		loadMap("map")
		loadImages()
		
		rand = new Random()
		p = [sid: 4, x: 100, y:120, vx: 0, vy: 0, f: 1]
		i = [x:40, y:0]	
		bc = [x:30, y:(rand.nextInt(height)*10).toFloat()]
		sc = [x:180, y:(rand.nextInt(height)*10).toFloat()]
		
	}
	
	void moveClouds(){
		bc.x -= 0.2
		sc.x -= 0.2
		if(bc.x < -8) {
			bc = [x: 240, y:(rand.nextInt(height)*10).toFloat()]
			
		}
		if(sc.x < -8){
			sc = [x: 240, y: (rand.nextInt(height)*10).toFloat()]
		}
	}

	void update(float delta){
		moveClouds()
		move()
	}
	
	void render(){
		bgColor(0.1f,0.1f,0.1f)
		//FPS()
		image("moon", i.x,i.y)
		map(0,0)
			
		if(p.f == 1) {
			sprite(p.sid, p.x, p.y, false, false)
		}else{
			sprite(p.sid, p.x, p.y, true, false)
		}
				
		sprite16(3, bc.x, bc.y)
		sprite16(4, sc.x, sc.y)		
	}
}