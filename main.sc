// Welcome to the Back to the Future Hill Valley 0.2f r3 SCM, if you got this far you must atleast know enough to get started, if you don't, hopefully the comments spread throughout this SC and script files will help.
// Please Only ever open the Main.SC not the Main.SCM as you will lose any comments we have made or hopefully you have made in the future, it will also get rid of thread names and make you very sad. just don't do it.
// we may add a table of contents help guide in the future for people to better understand the mod and learn to make changes.
DEFINE MISSIONS 1
DEFINE MISSION 0 AT @INITIAL
//These are the New Opcodes originally written by Ilufir rewritten by Kyonko Yuuki, defining them here means no nasty setup for sanny builder.
{$USE CLEO}
{$INCLUDE ../CLEO/Common/CleoConstants.txt}
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
0171: set_player $PLAYER_CHAR z_angle_to 180.0
$PLAYER_ACTOR = Actor.EmulateFromPlayer($PLAYER_CHAR)
03AD: set_rubbish 0
0219: $681 = create_garage_type 5 door -914.129 -1263.54 10.706 to -906.3 -1266.9 14.421 depth -907.137 -1246.626
0219: $682 = create_garage_type 5 door -1014.341 -857.732 6.325 to -1014.341 -841.532 10.885 depth -1001.315 -857.732
03BB: set_garage $682 door_type_to_swing_open
0219: $683 = create_garage_type 5 door -886.157 -115.158 9.992 to -876.7 -119.83 15.58 depth -882.699 -108.312
0219: $684 = create_garage_type 5 door 323.9 427.4 10.0 to 313.9 430.53 15.7 depth 326.3 434.5
0219: $685 = create_garage_type 5 door -7.55 -1253.77 9.322 to 2.64 -1253.7 14.4 depth -7.55 -1261.2
$CYEAR = -1
$1955 = 0 // integer values
$1985 = 1000 // integer values
$2015 = 0 // integer values
$1955CAR = 0 // integer values
$1985CAR = 101 // integer values
$2015CAR = 0 // integer values
$ALLCAR = 101 // integer values
$MALL_SIGN = 2
032B: $BTTFREMOTEDEBUG = create_weapon_pickup #SCREWDRIVER 15 ammo 1 at -542.5803 268.1569 12.4336 //remove me before release :)

start_mission 0  // Initial

//------------------0.2F-r3-Feature-Scripts-Go-Here--------------------------//
wait 0
//-------------------------------------------------------------//
create_thread @CleoVariables       // Populate main.scm globals with Cleo globals
create_thread @CarYear             // Parked car and ped generators for time travel
//create_thread @Train               // Time Train
//create_thread @Truck               // GMC Value Van Delorean Transport Truck
//create_thread @Rogers              // 1885 train
//create_thread @Taxi                // 2015 taxi animation (incomplete)
//create_thread @Board               // hoverboard attach to cars
create_thread @Hover               // Flying 2015 cars
//create_thread @Garage              // DeLorean Garage
//create_thread @HillValley          // Real Time Clock and courthouse spawner
create_thread @DateCheckStart      // New Time Changing code
//create_thread @55TV
//create_thread @55TVOff
//create_thread @DebugMove           // helps us move objects/particles
//create_thread @DebugCamera
//create_thread @DebugParticle
create_thread @Interiors

3F80: stop_all_sounds

wait 0
0180: set_on_mission_flag_to $ONMISSION
fade 1 1000
if
    Player.Defined($PLAYER_CHAR)
then
    select_interior 0
    Player.CanMove($PLAYER_CHAR) = True
end
0109: player $PLAYER_CHAR money += 2000


:InfLoop
wait 10
jump @InfLoop

//{$INCLUDE script/2015PoliceCar.txt}
//{$INCLUDE script/55BarberPole.txt}
//{$INCLUDE script/55TV.txt}
{$INCLUDE script/Audiotex.txt}
//{$INCLUDE script/CanOpener.txt}
{$INCLUDE script/CarYear.txt}
{$INCLUDE script/CleoVariables.txt}
//{$INCLUDE script/DebugCamera.txt}
//{$INCLUDE script/DebugCoords.txt}
{$INCLUDE script/HoverboardAttach.txt}
{$INCLUDE script/HoverConversion.txt}
{$INCLUDE script/Interiors.txt}
{$INCLUDE script/InteriorSpawner.txt}
{$INCLUDE script/MallSign.txt}
{$INCLUDE script/RadiationSuit.txt}
{$INCLUDE script/TimeChangingMap.txt}
{$INCLUDE script/TimeChangingPickups.txt}
{$INCLUDE script/TwinPinesRipple.txt}
{$INCLUDE script/TwinPinesTrees.txt}
{$INCLUDE script/Walkman.txt}
//-------------Mission 0---------------
{$INCLUDE script/MISSION/initial.txt}
//-------------Mission 1---------------
// put missions here
