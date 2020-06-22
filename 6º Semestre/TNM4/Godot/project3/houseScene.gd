extends Spatial

# Called when the node enters the sce.ne tree for the first time.
func _ready():
	
	pass # Replace with function body.
func _physics_process(delta):
	interaction()
	
func interaction():

	if Input.is_action_just_pressed("interact"):
		get_tree().change_scene("res://MainScene.tscn")
	
