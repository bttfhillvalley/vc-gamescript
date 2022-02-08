// Welcome to the Back to the Future Hill Valley 0.2f r3 SCM, if you got this far you must atleast know enough to get started, if you don't, hopefully the comments spread throughout this SC and script files will help.
// Please Only ever open the Main.SC not the Main.SCM as you will lose any comments we have made or hopefully you have made in the future, it will also get rid of thread names and make you very sad. just don't do it.
// we may add a table of contents help guide in the future for people to better understand the mod and learn to make changes.
DEFINE MISSIONS 0
//These are the New Opcodes originally written by Ilufir rewritten by Kyonko Yuuki, defining them here means no nasty setup for sanny builder.
{$USE bitwise}
{$USE CLEO}
{$OPCODE 0124=2,write_memory_address %1d% value %2d%}                // used for writing a temporary address
{$OPCODE 0125=2,%2d% = read_memory_address %1d%}                     // used for reading a temporary address

{$OPCODE 3F01=1,set car %1d% suspension}                                        // set suspension
{$OPCODE 3F02=2,%2d% = car %1d% engine_state}                                   // get engine state
{$OPCODE 3F03=1,car %1d% turn_on_engine}                                        // turn on engine
{$OPCODE 3F04=2,%2d% = get_car %1d% gear}                                       // get current gear
{$OPCODE 3F05=2,set_car %1d% hover %2d%}                                        // this turns a vehicle into a helicopter (currently unused)
{$OPCODE 3F06=12,create_light type %1d% at %2d% %3d% %4d% dir %5d% %6d% %7d% range %8d% rgb %9d% %10d% %11d% fog %12d%} //create a light on a vehicle (used for wormhole lighting and reentry blue hue)
{$OPCODE 3F07=4,play_anim %1d% grp %2d% blend %3d% on char %4d%}                // plays an animation (currently unused)
{$OPCODE 3F08=2,add_build %1p% amnt %2p%}                                       // adds all buildings and objects in a .dat file
{$OPCODE 3F09=2,remove_build %1p% amnt %2p%}                                    // removes all buildings and objects in a .dat file
{$OPCODE 3F10=3,set_car %1d% component %2d% visible %3d%}                       // show or hide a car part by name
{$OPCODE 3F11=4,set_car %1d% component %2d% index %4d% visible %3d%}            // show or hide a car part with index
{$OPCODE 3F12=3,set car %1d% comp %2d% alpha %3d%}                              // sets a car part's alpha from 0-255 instead of hiding or showing it completely
{$OPCODE 3F13=3,set car %1d% comp %2d% alpha %3d%}                              // sets a car part with index alpha from 0-255 instead of hiding or showing it completely
{$OPCODE 3F14=5,move_car_part %1d% pos %2d% %3d% %4d% car %5d%}                 // moves a car part by its frame
{$OPCODE 3F15=6,move_car_part %1d% index %6d% pos %2d% %3d% %4d% car %5d%}      // moves a car part with index by its frame
{$OPCODE 3F16=5,rotate_car_part %1d% angle %2d% %3d% %4d% car %5d%}             // rotates a car part by its frame
{$OPCODE 3F17=6,rotate_car_part %1d% index %6d% angle %2d% %3d% %4d% car %5d%}  // rotates a car part with index by its frame

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
00C0: set_current_time 8 0
0572: set_taxi_boost_jump 1
04E4: unknown_refresh_game_renderer_at 892.0571 -1136.9220
Camera.SetAtPos(892.0571, -1136.9220, 10.2717)

$PLAYER_CHAR = Player.Create(#NULL, 892.0571, -1136.9220, 10.2717) // Mall
//$PLAYER_CHAR = Player.Create(#NULL, -1116.9710, 699.6134, 15.1518) // Garage
//$PLAYER_CHAR = Player.Create(#NULL, -1376.2, -155.2, 248.6) // McFly interior 85
//$PLAYER_CHAR = Player.Create(#NULL, -1560.4, -301.621, 17.23) // Lyon estates
//$PLAYER_CHAR = Player.Create(#NULL, -306.1443, -750.9825, 11.8055) // Hill valley
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
{0219: $DELOREAN_GARAGE = create_garage_type 1 door -966.016 -861.529 5.761 to -966.016 -841.683 11.273 depth -978.454 -861.529  //Vice City DeLorean Garage
03BB: set_garage $DELOREAN_GARAGE door_type_to_swing_open
02A8: $DelGarageMap = create_marker 7 at -1007.3 -869.9 12.8 //Delorean Garage Icon
04CE: $BrownEstateMap = create_icon_marker_without_sphere 5 at -378.5 -551.3 18.2 //Brown Estate Icon  }

//------------------0.2F-r3-Feature-Scripts-Go-Here--------------------------//
wait 0
//-------------------------------------------------------------//
//create_thread @Train               // Time Train
create_thread @Truck               // GMC Value Van Delorean Transport Truck
create_thread @Rogers              // 1885 train
create_thread @Taxi                // 2015 taxi animation (incomplete)
create_thread @Board               // hoverboard attach to cars
create_thread @Speed               // hud and in car digital speedometers
create_thread @Headlights          // Headlights
create_thread @FusionGlow          // Time Train Flux Capacitor
//create_thread @Glow                //Various glows
create_thread @TrainGlow           // Train Coils
create_thread @Mode                // Instant & Cutscene Time Travel Modes
create_thread @TimeCircuits        // On screen Time Circuits
create_thread @TimeCircuitsModel   // in car time circuits
create_thread @CurrentTime         // Present Time information
create_thread @MemoryManipulation  // The Keypad Core
create_thread @Display             // Keys for turning on/off speedometer and time circuits
//create_thread @HoverConversion     // New Hover Conversion code
//create_thread @Hover               // Flying 2015 cars
//create_thread @RadioControl        // RC Mode
create_thread @Shutdown            // All the parts that move when exiting a DeLorean
//create_thread @GetPlutonium        // Plutonium pickup and lybians
//create_thread @Garage              // DeLorean Garage
create_thread @DrawRefresh         // On screen text display rendering
create_thread @Ignition            // All the parts that move when entering a DeLorean
create_thread @Environment         // Weather, parked car and ped generators for time trave
create_thread @HillValley          // Real Time Clock and courthouse spawner
create_thread @Conversion          // Flying DeLorean and Train hover conversion animations
create_thread @Radio               // Copies the current radio station information for time travel/hover converion transitions
create_thread @FlyPolice           // Flying 2015 police car conversion
create_thread @carclockhr          // Bulova Alarm Clock hour hand and digital console clock hour
create_thread @carclockmin         // Bulova Alarm Clock minute hand and digital console clock minute
create_thread @Compass_rotation    // In Car Compass
create_thread @Shifter             // Transmission, RPM, and Parking Brake animations
create_thread @DateCheckStart      // New Time Changing code
//create_thread @DebugMove helps us move objects/particles
//create_thread @55TV
//create_thread @55TVOff
create_thread @CarSpawn
wait 0
0180: set_on_mission_flag_to $ONMISSION
set_weather $WEATHER
if
    not Actor.Dead($PLAYER_ACTOR)
then
    0352: set_actor $PLAYER_ACTOR skin_to 'PLAYER8'  //Marty Red T-Shirt
    038B: load_requested_models
    0353: refresh_actor $PLAYER_ACTOR
end
fade 1 1000
if
    Player.Defined($PLAYER_CHAR)
then
    select_interior 0
    Player.CanMove($PLAYER_CHAR) = True
    //Load Textures for on screen time travel hud here
    //!Important! there is a 5 character limit for texture names
    0390: load_txd_dictionary 'hud'  // HUD.TXD
    038F: load_texture 'lcd' as 1    // Time Circuit Labels
    038F: load_texture 'empon' as 2  // Empty Light on
    038F: load_texture 'empof' as 3  // Empty Light off
    038F: load_texture 'batry' as 4  // Battery Light on
    038F: load_texture 'batro' as 5  // Battery Light Off
    038F: load_texture 'lambd' as 6  // LAMBDA Light (usually this is for engine damage but currently only shown if time circuits are on)
    01B7: release_weather
end
{$INCLUDE script/2015PoliceCar.txt}
{$INCLUDE script/55BarberPole.txt}
{$INCLUDE script/55TV.txt}
{$INCLUDE script/Audiotex.txt}
{$INCLUDE script/CanOpener.txt}
{$INCLUDE script/CarClock.txt}
{$INCLUDE script/CarSpawn.txt}
{$INCLUDE script/ClockTower.txt}
{$INCLUDE script/Conversion.txt}
{$INCLUDE script/CommonUtils.txt}
{$INCLUDE script/CompassRotation.txt}
{$INCLUDE script/CurrentTime.txt}
{$INCLUDE script/DeloreanVariation.txt}
{$INCLUDE script/DigitalSpeedometer.txt}
{$INCLUDE script/DisplayToggle.txt}
{$INCLUDE script/DrawText.txt}
{$INCLUDE script/DrawTexture.txt}
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
{$INCLUDE script/MemoryManipulation.txt}
{$INCLUDE script/Taxi.txt}
{$INCLUDE script/Train.txt}
{$INCLUDE script/Truck.txt}
{$INCLUDE script/Radio.txt}
{$INCLUDE script/RadioControl.txt}
{$INCLUDE script/Rogers.txt}
{$INCLUDE script/Shifter.txt}
{$INCLUDE script/ShutDown.txt}
{$INCLUDE script/TimeChangingMap.txt}
{$INCLUDE script/TimeCircuitsModel.txt}
{$INCLUDE script/TimeCircuits.txt}
{$INCLUDE script/TimeEffects.txt}
{$INCLUDE script/TimeMachineFuel.txt}
{$INCLUDE script/TimeTravel.txt}
{$INCLUDE script/TimeTravelMode.txt}
{$INCLUDE script/TrainEffects.txt}
//-------------Mission 0---------------
// put missions here
