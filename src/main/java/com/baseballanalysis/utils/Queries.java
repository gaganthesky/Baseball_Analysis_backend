package com.baseballanalysis.utils;

public class Queries {
	public static String teamPerformance = "select yearid,lgid,teamid,name,G,W,L,R,AB,H,HR,SO,CS,RA,ER,CG,SHO,HA,HRA,FP,attendance,SOA,RANK from teams where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%')) order by yearid";
	public static String playerSalary = "select m.namegiven,sum(s.salary) from \"Salaries\" s, master m where s.playerid = m.playerid and yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%')) group by s.playerid,m.namegiven";
	public static String teamOrientationBattingPitching = "select batting.teamid,battingRank,pitchingRank,runs,outs from (select runs,battingRank, teamid from (select runs,rownum as battingRank, teamid from (select sum(t.R) as runs, t.teamid from teams t where t.yearid>=%s and t.yearid<=%s group by t.teamid order by runs desc)) where"
			+ "('%s' like CONCAT(CONCAT('%%',teamid),'%%'))) batting,"
			+ "(select outs,pitchingRank, teamid from (select outs,rownum as pitchingRank, teamid from (select sum(t.SOA) as outs, t.teamid from teams t where t.yearid>=%s and t.yearid<=%s group by t.teamid order by outs desc)) where"
			+ "('%s' like CONCAT(CONCAT('%%',teamid),'%%'))) pitching where batting.teamid = pitching.teamid";

	public static String teamTendency = "select playerid, CONCAT(namefirst,CONCAT(' ',namelast)), bats, throws from master where playerid in"
			+ "(select distinct playerid from appearances where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%')))";

	public static String playerBirthCountry = "select birthcountry, count(playerid) from master where playerid in(select distinct playerid from appearances where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%'))) group by BIRTHCOUNTRY";
	public static String playerBirthState = "select CONCAT('US-',birthstate), count(playerid) from master where playerid in(select distinct playerid from appearances where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%'))) group by CONCAT('US-',birthstate)";
	public static String playerBirthCity = "select birthcity, count(playerid) from master where playerid in(select distinct playerid from appearances where yearid>=%s and yearid<=%s and ('%s' like CONCAT(CONCAT('%%',teamid),'%%'))) group by birthcity";
	public static String allManagers = "select distinct managers.playerid, CONCAT(namefirst,CONCAT(' ',namelast)) from managers ,master where managers.playerid = master.playerid;";
	public static String playerSalariesOverTheYears = "select yearid,teamid, playerid, salary from \"Salaries\" where playerid = '%s' and yearid >= %s and yearid <=%s order by yearid";
	public static String playersWithWeightGroups = "select CONCAT((CAST(m.weight/10 as integer)*10 - 5),CONCAT('-',(CAST(m.weight/10 as integer)*10 + 5))) as weightGroup, count(distinct b.playerid) from batting b,master m where b.playerid = m.playerid and b.yearid>=%s and b.yearid<=%s and ('%s' like CONCAT(CONCAT('%%',b.teamid),'%%')) group by CAST(m.weight/10 as integer)";
	public static String playersWithHeightGroups = "select CONCAT((CAST(m.height/2 as integer)*2 - 1),CONCAT('-',(CAST(m.height/2 as integer)*2))) as heightGroup, count(distinct b.playerid) from batting b,master m where b.playerid = m.playerid and b.yearid>=%s and b.yearid<=%s and ('%s' like CONCAT(CONCAT('%%',b.teamid),'%%')) group by CAST(m.height/2 as integer)";
	public static String battingWeightGroups = "select CONCAT((CAST(m.weight/10 as integer)*10 - 5),CONCAT('-',(CAST(m.weight/10 as integer)*10 + 5))) as weightGroup, count(distinct b.playerid)as Num_of_Players, Sum(b.R), Sum(b.HR)  from batting b, master m where b.playerid = m.playerid and b.yearid>=%s and b.yearid<=%s and ('%s' like CONCAT(CONCAT('%%',b.teamid),'%%')) group by CAST(m.weight/10 as integer)";
	public static String battingHeightGroups = "select CONCAT((CAST(m.height/2 as integer)*2 - 1),CONCAT('-',(CAST(m.height/2 as integer)*2))) as heightGroup, count(distinct b.playerid)as Num_of_Players, Sum(b.R), Sum(b.HR)  from batting b, master m where b.playerid = m.playerid and b.yearid>=%s and b.yearid<=%s and ('%s' like CONCAT(CONCAT('%%',b.teamid),'%%')) group by CAST(m.height/2 as integer)";
	public static String pitchingWeightGroups = "select CONCAT((CAST(m.weight/10 as integer)*10 - 5),CONCAT('-',(CAST(m.weight/10 as integer)*10 + 5))) as weightGroup, count(distinct p.playerid)as Num_of_Players, Sum(p.SO), Sum(p.SHO)  from pitching p, master m where p.playerid = m.playerid and p.yearid>=2010 and p.yearid<=2014 and ('CHA' like CONCAT(CONCAT('%',p.teamid),'%')) group by CAST(m.weight/10 as integer)";
	public static String pitchingHeightGroups = "select CONCAT((CAST(m.height/2 as integer)*2 - 1),CONCAT('-',(CAST(m.height/2 as integer)*2))) as heightGroup, count(distinct p.playerid)as Num_of_Players, Sum(p.SO), Sum(p.SHO)  from pitching p, master m where p.playerid = m.playerid and p.yearid>=2010 and p.yearid<=2014 and ('CHA' like CONCAT(CONCAT('%',p.teamid),'%')) group by CAST(m.height/2 as integer)";
	public static String battingManagerTeam = "select b.yearid,b.playerid, b.teamid, m.playerid as managerid, mm.namegiven, b.r, b.hr from batting b, managers m,  master mm  where  mm.playerid = m.playerid and m.teamid = b.teamid and m.yearid = b.yearid and b.yearid >= %s and b.yearid <= %s and b.playerid = '%s'";
	public static String pitchingManagerTeam = "select p.yearid,p.playerid, p.teamid, m.playerid as managerid, mm.namegiven, p.so, p.sho from pitching p, managers m, master mm where  mm.playerid = m.playerid and m.teamid = p.teamid and m.yearid = p.yearid and p.yearid >= %s and p.yearid <= %s and p.playerid = '%s'";
	public static String searchPlayer = "select playerid, CONCAT(namefirst,CONCAT(' ',namelast)) as name from master where playerid in (select distinct playerid from %s where yearid>=%s and yearid<=%s) and (LOWER(namefirst) like LOWER('%%%s%%') or LOWER(namelast) like LOWER('%%%s%%')) and ROWNUM < 100";

}
