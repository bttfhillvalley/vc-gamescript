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
04E4: unknown_refresh_game_renderer_at -255.2254 -492.3188
Camera.SetAtPos(-255.2254, -492.3188, 11.1985)
$PLAYER_CHAR = Player.Create(#NULL, -255.2254, -492.3188, 11.1985) // Twin Pines Mall
//$PLAYER_CHAR = Player.Create(#NULL, -771.3590, 85.7275, 87.6737) // Twin Pines Mall
0171: set_player $PLAYER_CHAR z_angle_to 90.0
$PLAYER_ACTOR = Actor.EmulateFromPlayer($PLAYER_CHAR)
03AD: set_rubbish 0
$CYEAR = -1
$1955 = 0 // integer values
$1985 = 1000 // integer values
$2015 = 0 // integer values
$1955CAR = 0 // integer values
$1985CAR = 101 // integer values
$2015CAR = 0 // integer values
$ALLCAR = 101 // integer values
$MALL_SIGN = 2
032B: $BTTFREMOTEDEBUG = create_weapon_pickup #SCREWDRIVER 15 ammo 1 at -255.2254 -492.3188 11.1985 //remove me before release :)

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
