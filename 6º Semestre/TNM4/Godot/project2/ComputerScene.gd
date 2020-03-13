extends Spatial


# Declare member variables here. Examples:
# var a = 2
# var b = "text"
var label

# Called when the node enters the scene tree for the first time.
func _ready():
	pass # Replace with function body.

func _physics_process(delta):
	interaction()

func interaction():
	if Input.is_action_just_pressed("ExitInteract"):
		get_tree().change_scene("res://Spatial.tscn")
# Called every frame. 'delta' is the elapsed time since the previous frame.
#func _process(delta):
#	pass


func _on_TextEdit_text_changed():
	label = $TextEdit
	label.add_color_override("font_color", Color.lightcoral)
	pass # Replace with function body.
