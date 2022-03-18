// Welcome to the Back to the Future Hill Valley 0.2f r3 SCM, if you got this far you must atleast know enough to get started, if you don't, hopefully the comments spread throughout this SC and script files will help.
// Please Only ever open the Main.SC not the Main.SCM as you will lose any comments we have made or hopefully you have made in the future, it will also get rid of thread names and make you very sad. just don't do it.
// we may add a table of contents help guide in the future for people to better understand the mod and learn to make changes.
DEFINE MISSIONS 1
DEFINE MISSION 0 AT @INITIAL
//These are the New Opcodes originally written by Ilufir rewritten by Kyonko Yuuki, defining them here means no nasty setup for sanny builder.
{$USE bitwise}
{$USE CLEO}
{$OPCODE 0124=2,write_memory_address %1d% value %2d%}                // used for writing a temporary address
{$OPCODE 0125=2,%2d% = read_memory_address %1d%}                     // used for reading a temporary address

{$OPCODE 3F01=2,car %1d% raise_suspension %2d%}                                 // set suspension
{$OPCODE 3F02=2,%2d% = car %1d% engine_state}                                   // get engine state
{$OPCODE 3F03=1,car %1d% turn_on_engine}                                        // turn on engine
{$OPCODE 3F04=2,%2d% = get_car %1d% gear}                                       // get current gear
{$OPCODE 3F05=2,set_car %1d% hover %2d%}                                        // this turns a vehicle into a helicopter (currently unused)
{$OPCODE 3F06=3,%3d% = get_car %1d% door %2d% status}                           // get door status
{$OPCODE 3F07=4,play_anim %1d% grp %2d% blend %3d% on char %4d%}                // plays an animation (currently unused)
{$OPCODE 3F08=2,add_build %1p% amnt %2p%}                                       // adds all buildings and objects in a .dat file
{$OPCODE 3F09=2,remove_build %1p% amnt %2p%}                                    // removes all buildings and objects in a .dat file
{$OPCODE 3F10=3,set_car %1d% component %2d% visible %3d%}                       // show or hide a car part by name
{$OPCODE 3F11=4,set_car %1d% component %2d% index %4d% visible %3d%}            // show or hide a car part with index
{$OPCODE 3F12=3,set car %1d% component %2d% alpha %3d%}                         // sets a car part's alpha from 0-255 instead of hiding or showing it completely
{$OPCODE 3F13=4,set car %1d% component %2d% index %4d% alpha %3d%}              // sets a car part with index alpha from 0-255 instead of hiding or showing it completely
{$OPCODE 3F14=5,move_car_part %1d% pos %2d% %3d% %4d% car %5d%}                 // moves a car part by its frame
{$OPCODE 3F15=6,move_car_part %1d% index %6d% pos %2d% %3d% %4d% car %5d%}      // moves a car part with index by its frame
{$OPCODE 3F16=5,rotate_car_part %1d% angle %2d% %3d% %4d% car %5d%}             // rotates a car part by its frame
{$OPCODE 3F17=6,rotate_car_part %1d% index %6d% angle %2d% %3d% %4d% car %5d%}  // rotates a car part with index by its frame
{$OPCODE 3F18=3,set_car %1d% component %2d% glow %3d%}                          // make a car component glow
{$OPCODE 3F19=4,set_car %1d% component %2d% index %4d% glow %3d%}               // make a car part with index glow
{$OPCODE 3F20=4,get_car %1d% orientation_to %2d% %3d% %4d%}                     // get car XYZ orientation
{$OPCODE 3F21=4,set_car %1d% orientation_to %2d% %3d% %4d%}                     // set car XYZ orientation
{$OPCODE 3F22=1,set_car %1d% wheelie}                                           // flip car or do a wheelie
{$OPCODE 3F23=1,set_car %1d% remote}                                            // enable RC mode for a car
{$OPCODE 3F24=1,remove_car %1d% remote}                                         // turn off RC mode for a car.  Must be done before the car is removed.
{$OPCODE 3F25=2,apply_forward_force %2d% car %1d%}                              // Apply thrust to car
{$OPCODE 3F26=2,get_car %1d% wheel_status_to %2d%}                              // Get wheel status
{$OPCODE 3F27=2,set_car %1d% wheel_status %2d%}                                 // Set wheel status
{$OPCODE 3F28=12,create_light type %1d% at %2d% %3d% %4d% dir %5d% %6d% %7d% range %8d% rgb %9d% %10d% %11d% fog %12d%} //create a light on a vehicle (used for wormhole lighting and reentry blue hue)
{$OPCODE 3F29=1,is_player_in_remote_mode_with_car %1d%}                         // Is player in RC mode with specified car?
{$OPCODE 3F30=2,set_car %1d% rotate_force %2d%}                                 // Add rotational Z force to car
{$OPCODE 3F31=4,get_car %1d% rotation_matrix %2d% %3d% %4d%}                    // Get rotational matrix as a very big int.  XXXYYYZZZ, Forward, Right Up
{$OPCODE 3F32=4,set_car %1d% rotation_matrix %2d% %3d% %4d%}                    // Set rotational matrix
{$OPCODE 3F33=4,get_car %1d% velocity_vector %2d% %3d% %4d%}                    // Get velocity vector
{$OPCODE 3F34=2,%2d% = car %1d% velocity_vector}                                // Get velocity vector including speed
{$OPCODE 3F35=2,set_car %1d% velocity_vector %2d%}                              // Set velocity vector including speed
{$OPCODE 3F36=2,get_car %1d% steering_angle %2d%}                               // Get Steering wheel angle
//{$OPCODE 3F37=1,replace_dict %1d%} // %1d% texture %2d% with_dict %3d% texture %4d%}     // HUD.TXD
//{$OPCODE 3F38=9,add_part_anim %1d% mov %2d% %3d% %4d% ang %5d% %6d% %7d% tim %8d% car %9d%}
{$OPCODE 3F80=0,stop_all_sounds}                                                // Stop all sounds
{$OPCODE 3F81=1,stop_sound %1d%}                                                // Stop sound
{$OPCODE 3F82=1,is_sound_playing %1d%}                                          // Is sound still playing?
{$OPCODE 3F83=1,is_sound_stopped %1d%}                                          // Is sound stopped?
{$OPCODE 3F84=2,play_sound_store %1d% loop %2d%}                                // Play Sound
{$OPCODE 3F85=6,play_sound %1d% pos %2d% %3d% %4d% loop %5d% size %6d%}         // Play Sound at location
{$OPCODE 3F86=7,attach_sound %1d% to_car %7d% pos %2d% %3d% %4d% loop %5d% size %6d%} // Attach sound to car
{$OPCODE 3F91=2,stop_sound %1d% index %2d%}                                     // Stop sound w/ index
{$OPCODE 3F92=2,is_sound_playing %1d% index %2d%}                               // Is sound still playing? w/ index
{$OPCODE 3F93=2,is_sound_stopped %1d% index %2d%}                               // Is sound stopped? w/ index
{$OPCODE 3F94=3,%3d% = play_sound_store %1d% loop %2d%}                         // Play Sound w/ index
{$OPCODE 3F95=7,%7d% = play_sound %1d% pos %2d% %3d% %4d% loop %5d% size %6d%}  // Play sound at location w/ index
{$OPCODE 3F96=8,%8d% = attach_sound %1d% to_car %7d% pos %2d% %3d% %4d% loop %5d% size %6d%} // Attach sound to car w/ index

//Audio Library
//\CLEO\CLEO_AUDIO\test.mp3
{$OPCODE 0AAC=3,play_audio_stream_1channel %1d% loop %2d% volume %3d%}
{$OPCODE 0AAD=0,stop_audio_stream}
{$OPCODE 7AAA=1,play_mod_music %1d%}
{$OPCODE 7AAB=0,stop_mod_music}
{$OPCODE 7AAC=3,play_audio_stream_2channel %1d% loop %2d% volume %3d%}
{$OPCODE 7ABB=2,set_mod_position %1d% %2d%}
//-------------MAIN---------------
thread 'MAIN'
fade 0 0
042C: set_total_missions_to 88
030D: set_total_mission_points_to 154
030C: set_mission_points += 154
01F0: set_max_wanted_level_to 6
set_wb_check_to 0
00C0: set_current_time 10 0
0572: set_taxi_boost_jump 1
04E4: unknown_refresh_game_renderer_at -542.5803 268.1569
Camera.SetAtPos(-542.5803, 268.1569, 12.4336)
$PLAYER_CHAR = Player.Create(#NULL, -542.5803, 268.1569, 12.4336) // Twin Pines Mall
//$PLAYER_CHAR = Player.Create(#NULL, -771.3590, 85.7275, 87.6737) // Twin Pines Mall
0171: set_player $PLAYER_CHAR z_angle_to 180.0
$PLAYER_ACTOR = Actor.EmulateFromPlayer($PLAYER_CHAR)
03AD: set_rubbish 0
$1955 = 1955 // integer values
$1985 = 0 // integer values
$2015 = 0 // integer values
$1955CAR = 0 // integer values
$1985CAR = 101 // integer values
$2015CAR = 0 // integer values
$ALLCAR = 101 // integer values
$R1 = 64 // integer values
$G1 = 128 // integer values
$B1 = 255 // integer values
$R2 = 64 // integer values
$G2 = 128 // integer values
$B2 = 255 // integer values
$TR = 145 // integer values
$TG = 135 // integer values
$TB = 235 // integer values
$CHOOSE = -1 // integer values
$TURN_OFF = 0 // integer values
$FLY_SWITCH = 1 // integer values
$RC_CONTROL = 1 // integer values
$AI_CONTROL = 0 // integer values
$MODE_CONTROL = 1 // integer values
$CAMERA_CONTROL = 1 // integer values
$FUELING_CONTROL = 1 // integer values
$GARAGE_CONTROL = 1 // integer values
$DOOR_CONTROL = 1 // integer values
$PLUTONIUM_CONTROL = 1 // integer values
$BEEPER_CONTROL = 1 // integer values
$VARIATION = -1 // unused
$PLATE = -1 // unused
$MODE = 0 // integer values
$PLUTONIUM = 12 // integer values
$CMONTH = 10 // integer values
$CDAY = 25 // integer values
$CYEAR = 1985 //This is the starting year
// Memory Addresses used by the Time Circuit asi
$TRAVEL_CONTROL = 1       // 0x08233C0
$TCON = 0                 // 0x08233C4
$MONTH = 10               // 0x08233C8
$DAY = 17                 // 0x08233CC
$YEAR = 2009              // 0x08233D0
$HOUR = 18                // 0x08233D4
$MINUTE = 50              // 0x08233D8
$TRAVEL = 0               // 0x08233DC
$FRIED = 0                // 0x08233E0
$FLASH = 0                // 0x08233E4
$ROGERS_CREATE = 0        // 0x08233E8
$ROGERS_CHUG = 0          // 0x08233EC
$ROGERS_PUFF = 0          // 0x08233F0
$KEY_TIMETRAIN = 84       // 0x08233F4 // T
$KEY_HOVERCONVERSION = 67 // 0x08233F8 // C
$KEY_DOCSTRUCK = 89       // 0x08233FC // Y
$KEY_RADIOCONTROL = 82    // 0x0823400 // R
$KEY_TOGGLEDISPSPEED = 91 // 0x0823404 // [
$KEY_TOGGLEDISPTCS = 93   // 0x0823408 // [
$KEY_TOGGLEMODE = 77      // 0x082340C // M
$HOOK_WATCHDOG = 0        // 0x0823410 //not needed anymore
$DELOREAN_GARAGE = 0
$POSX = 430.0 // floating-point values
$POSY = 190.0 // floating-point values
$ROTATE_SPEED = 0.0 // floating-point values
$WHEEL_SPARKS = 0.0 // floating-point values
$CONVERSION = 0
$HEIGHT_LIMIT = 220.0
$HOVER_ACCEL_KEY = 19 // 4 - Radio Key, 19 - Submission key.  Not completely compatible with ClassicAxis
$STAGE_TWO_BOOST = 1
$MALL_SIGN = 2
032B: $BTTFREMOTEDEBUG = create_weapon_pickup 260 15 ammo 1 at -542.5803 268.1569 12.4336 //remove me before release :)
start_mission 0  // Initial

//------------------0.2F-r3-Feature-Scripts-Go-Here--------------------------//
wait 0
//-------------------------------------------------------------//
//create_thread @Train               // Time Train
//create_thread @Truck               // GMC Value Van Delorean Transport Truck
//create_thread @Rogers              // 1885 train
//create_thread @Taxi                // 2015 taxi animation (incomplete)
//create_thread @Board               // hoverboard attach to cars
create_thread @Speed               // hud digital speedometers
//create_thread @FusionGlow          // Time Train Flux Capacitor
//create_thread @Glow                //Various glows
//create_thread @TrainGlow           // Train Coils
create_thread @Mode                // Instant & Cutscene Time Travel Modes
create_thread @TimeCircuits        // On screen Time Circuits
for $SID = 1 to 10 step 1
    create_thread @StatusIndicatorDisplay $SID   // in car time circuits
end
create_thread @CurrentTime         // Present Time information
create_thread @MemoryManipulation  // The Keypad Core
create_thread @Display             // Keys for turning on/off speedometer and time circuits
create_thread @Hover               // Flying 2015 cars
create_thread @RadioControl        // RC Mode
create_thread @GetPlutonium        // Plutonium pickup and lybians
//create_thread @Garage              // DeLorean Garage
create_thread @DrawRefresh         // On screen text display rendering
create_thread @Environment         // Weather, parked car and ped generators for time trave
create_thread @HillValley          // Real Time Clock and courthouse spawner
create_thread @Conversion          // Flying DeLorean and Train hover conversion animations
create_thread @DateCheckStart      // New Time Changing code
//create_thread @DebugMove           // helps us move objects/particles
//create_thread @55TV
//create_thread @55TVOff
//create_thread @DebugCamera
//create_thread @DebugParticle
create_thread @CarSpawn
create_thread @Interiors
0A8C: write_memory 0x54F429 size 5 value 0x90 virtual_protect 1 // Disable plane trails
0A8C: write_memory 0x58E59B size 5 value 0x90 virtual_protect 1 // Disable Tail light point lights
0A8C: write_memory 0x58E611 size 5 value 0x90 virtual_protect 1 // Disable Brake light point lights
0A8C: write_memory 0x69C70C size 4 value $HEIGHT_LIMIT virtual_protect 1 // Change height limit for Delorean

// These next ones disables the collapsing of frames on particular dummies
0A8C: write_memory 0x699730 size 4 value 0x80 virtual_protect 1 // Front Bumper
0A8C: write_memory 0x69973c size 4 value 0x00 virtual_protect 1 // Bonnet
0A8C: write_memory 0x699748 size 4 value 0x00 virtual_protect 1 // Wing RF
0A8C: write_memory 0x699754 size 4 value 0x40 virtual_protect 1 // Wing RR
0A8C: write_memory 0x699760 size 4 value 0x5050 virtual_protect 1 // Door RF
0A8C: write_memory 0x69976c size 4 value 0x3150 virtual_protect 1 // Door RR
0A8C: write_memory 0x699778 size 4 value 0x00 virtual_protect 1 // Wing LF
0A8C: write_memory 0x699784 size 4 value 0x20 virtual_protect 1 // Wing LR
0A8C: write_memory 0x699790 size 4 value 0x5030 virtual_protect 1 // Door LF
0A8C: write_memory 0x69979c size 4 value 0x3130 virtual_protect 1 // Door LR
0A8C: write_memory 0x6997a8 size 4 value 0x100 virtual_protect 1 // Boot
0A8C: write_memory 0x6997b4 size 4 value 0x100 virtual_protect 1 // Rear Bumper
0A8C: write_memory 0x6997c0 size 4 value 0xc80 virtual_protect 1 // Windscreen
3F80: stop_all_sounds

wait 0
0180: set_on_mission_flag_to $ONMISSION
set_weather $WEATHER
fade 1 1000
if
    Player.Defined($PLAYER_CHAR)
then
    select_interior 0
    Player.CanMove($PLAYER_CHAR) = True
    //Load Textures for on screen time travel hud here
    //!Important! there is a 5 character limit for texture names
    {$INCLUDE script/TextureDictionary.txt}
    01B7: release_weather
end

:InfLoop
wait 10
jump @InfLoop
{$INCLUDE script/2015PoliceCar.txt}
{$INCLUDE script/55BarberPole.txt}
{$INCLUDE script/55TV.txt}
{$INCLUDE script/Audiotex.txt}
{$INCLUDE script/CanOpener.txt}
{$INCLUDE script/CarClock.txt}
{$INCLUDE script/CarInterior.txt}
{$INCLUDE script/CarSpawn.txt}
{$INCLUDE script/ClockTower.txt}
{$INCLUDE script/Conversion.txt}
{$INCLUDE script/CommonUtils.txt}
{$INCLUDE script/CompassRotation.txt}
{$INCLUDE script/CurrentTime.txt}
{$INCLUDE script/DeloreanVariation.txt}
{$INCLUDE script/DebugCamera.txt}
{$INCLUDE script/DigitalSpeedometer.txt}
{$INCLUDE script/DisplayToggle.txt}
{$INCLUDE script/DoorCheck.txt}
{$INCLUDE script/DrawText.txt}
{$INCLUDE script/DrawTexture.txt}
{$INCLUDE script/DrivingLights.txt}
{$INCLUDE script/DebugCoords.txt}
{$INCLUDE script/Environment.txt}
{$INCLUDE script/FlyAnim.txt}
{$INCLUDE script/FusionGlow.txt}
{$INCLUDE script/Garage.txt}
{$INCLUDE script/GetPlutonium.txt}
{$INCLUDE script/HillValley.txt}
{$INCLUDE script/Headlights.txt}
{$INCLUDE script/HoverboardAttach.txt}
{$INCLUDE script/HoverConversion.txt}
{$INCLUDE script/Ignition.txt}
{$INCLUDE script/Interiors.txt}
{$INCLUDE script/InteriorSpawner.txt}
{$INCLUDE script/MallSign.txt}
{$INCLUDE script/MemoryManipulation.txt}
{$INCLUDE script/ParkingBrake.txt}
{$INCLUDE script/RadioControl.txt}
{$INCLUDE script/RadiationSuit.txt}
{$INCLUDE script/Rogers.txt}
{$INCLUDE script/Shifter.txt}
{$INCLUDE script/Taxi.txt}
{$INCLUDE script/TimeChangingMap.txt}
{$INCLUDE script/TimeChangingPickups.txt}
{$INCLUDE script/TimeCircuitsModel.txt}
{$INCLUDE script/TimeCircuits.txt}
{$INCLUDE script/TimeEffects.txt}
{$INCLUDE script/TimeLightning.txt}
{$INCLUDE script/TimeMachineFuel.txt}
{$INCLUDE script/TimeSID.txt}
{$INCLUDE script/TimeTravel.txt}
{$INCLUDE script/TimeTravelMode.txt}
{$INCLUDE script/Train.txt}
{$INCLUDE script/TrainEffects.txt}
{$INCLUDE script/Truck.txt}
{$INCLUDE script/TwinPinesRipple.txt}
{$INCLUDE script/TwinPinesTrees.txt}
{$INCLUDE script/Walkman.txt}
{$INCLUDE script/Windows.txt}
//-------------Mission 0---------------
{$INCLUDE script/MISSION/initial.txt}
//-------------Mission 1---------------
// put missions here
