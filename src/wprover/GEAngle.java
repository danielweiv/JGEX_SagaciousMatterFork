package wprover;

import gprover.Cm;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.w3c.dom.Element;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2005-1-15
 * Time: 11:22:15
 * To change this template use File | Settings | File Templates.
 */
public class GEAngle extends GraphicEntity {

    private GEPoint p1, p2, p3, p4;
    GELine lstart;
    GELine lend;
    GEPoint pstart, pend;
    int radius = 20;
    private int show_type = -1; // 0: none  1: value ; 2:text , -1: default, 3: name with value;
    private int value1; // 0. Color or Num of Arc
    private int ftype = UtilityMiscellaneous.ANGLE_TYPE;  //// 0: traditional ;  1: Full angle, 2: Multiple arc, 3. Fill.
    private double dx, dy;

    GEText ptext;

    public GEAngle() {
        super(GraphicEntity.ANGLE);
    }

    public GEAngle(DrawPanel dp, Element thisElement, Map<Integer, GraphicEntity> mapGE) {
    	super(dp, thisElement);

    	int index = DrawPanelFrame.safeParseInt(thisElement.getAttribute("start_line"), 0);
		GraphicEntity ge = mapGE.get(index);
		if (ge instanceof GELine)
			lstart = (GELine)ge;
		else
			bIsValidEntity = false;

		index = DrawPanelFrame.safeParseInt(thisElement.getAttribute("end_line"), 0);
		ge = mapGE.get(index);
		if (ge instanceof GELine)
			lend = (GELine)ge;
		else
			bIsValidEntity = false;

		index = DrawPanelFrame.safeParseInt(thisElement.getAttribute("start_point"), 0);
		ge = mapGE.get(index);
		if (ge instanceof GEPoint)
			pstart = (GEPoint)ge;
		else
			bIsValidEntity = false;

		index = DrawPanelFrame.safeParseInt(thisElement.getAttribute("end_point"), 0);
		ge = mapGE.get(index);
		if (ge instanceof GEPoint)
			pend = (GEPoint)ge;
		else
			bIsValidEntity = false;

		radius = DrawPanelFrame.safeParseInt(thisElement.getAttribute("radius"), 20, 1, 50); // Default radius of 20 (min allowed is 1, max allowed is 50)
		show_type = DrawPanelFrame.safeParseInt(thisElement.getAttribute("show_type"), -1, -1, 3); // Default of -1 (min allowed is -1, max allowed is 3)
		ftype = DrawPanelFrame.safeParseInt(thisElement.getAttribute("ftype"), 0, 0, 3);
		value1 = DrawPanelFrame.safeParseInt(thisElement.getAttribute("value1"), 0);

        m_name = getAngleName();//"[" + p1.m_name + p2.m_name + "," + p3.m_name + p4.m_name + "]";
        ptext = new GEText(this, 2, 2, GEText.CNAME_TEXT);
        ptext.setFont(UtilityMiscellaneous.angleNameFont);
        ptext.setText1(m_name);
    }    
    
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(dx);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(dy);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ftype;
		result = prime * result + ((lend == null) ? 0 : lend.hashCode());
		result = prime * result + ((lstart == null) ? 0 : lstart.hashCode());
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
		result = prime * result + ((p3 == null) ? 0 : p3.hashCode());
		result = prime * result + ((p4 == null) ? 0 : p4.hashCode());
		result = prime * result + ((pend == null) ? 0 : pend.hashCode());
		result = prime * result + ((pstart == null) ? 0 : pstart.hashCode());
		result = prime * result + ((ptext == null) ? 0 : ptext.hashCode());
		result = prime * result + radius;
		result = prime * result + show_type;
		result = prime * result + value1;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof GEAngle))
			return false;
		GEAngle other = (GEAngle) obj;
		if (Double.doubleToLongBits(dx) != Double.doubleToLongBits(other.dx))
			return false;
		if (Double.doubleToLongBits(dy) != Double.doubleToLongBits(other.dy))
			return false;
		if (ftype != other.ftype)
			return false;
		if (lend == null) {
			if (other.lend != null)
				return false;
		} else if (!lend.equals(other.lend))
			return false;
		if (lstart == null) {
			if (other.lstart != null)
				return false;
		} else if (!lstart.equals(other.lstart))
			return false;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		if (p3 == null) {
			if (other.p3 != null)
				return false;
		} else if (!p3.equals(other.p3))
			return false;
		if (p4 == null) {
			if (other.p4 != null)
				return false;
		} else if (!p4.equals(other.p4))
			return false;
		if (pend == null) {
			if (other.pend != null)
				return false;
		} else if (!pend.equals(other.pend))
			return false;
		if (pstart == null) {
			if (other.pstart != null)
				return false;
		} else if (!pstart.equals(other.pstart))
			return false;
		if (ptext == null) {
			if (other.ptext != null)
				return false;
		} else if (!ptext.equals(other.ptext))
			return false;
		if (radius != other.radius)
			return false;
		if (show_type != other.show_type)
			return false;
		if (value1 != other.value1)
			return false;
		return true;
	}

	public void setValue1(int c) {
        value1 = c;
    }

    public int getTextType() {
        return show_type;
    }

    public void setTextType(int n) {
        show_type = n;
    }

    public int getValue1() {
        return value1;
    }

    public void setAngleType(int n) {
        ftype = n;
    }

    public int getAngleType() {
        return ftype;
    }


    boolean sameAngle(GEAngle g) {
        return g.pstart == pstart && g.pend == pend && g.lstart == lstart && g.lend == lend;
    }

    public boolean isSame(GEPoint p1, GEPoint p2, GEPoint p3, GEPoint p4) {
        if (this.p1 != null) {
            if ((this.p1 == p1 && this.p2 == p2 || this.p1 == p2 && this.p2 == p1)
                    && (this.p3 == p3 && this.p4 == p4 || this.p3 == p4 && this.p4 == p3))
                return true;
            if ((this.p1 == p3 && this.p2 == p4 || this.p1 == p4 && this.p2 == p3)
                    && (this.p3 == p1 && this.p4 == p2 || this.p3 == p2 && this.p4 == p1))
                return true;
        }
        if (lstart.pointOnLine(p1) && lstart.pointOnLine(p2) && lend.pointOnLine(p3) && lend.pointOnLine(p4))
            return true;

        if (lstart.pointOnLine(p3) && lstart.pointOnLine(p4) && lend.pointOnLine(p1) && lend.pointOnLine(p2))
            return true;
        
        return false;
    }

    public boolean isSame_m(GEPoint p1, GEPoint p2, GEPoint p3, GEPoint p4) {
        return false;
    }

    public GEAngle(GELine l1, GELine l2, GEPoint p1, GEPoint p2, GEPoint p3, GEPoint p4) { // p1 > p2  p3 > p4.   p1 > p3  || p1 == p3 p2 > p4
        super(GraphicEntity.ANGLE);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;

        lstart = l1;
        lend = l2;

        GEPoint pc = GELine.commonPoint(lstart, lend);

        if (p1 == pc) {
            pstart = p2;
        } else {
            pstart = p1;
        }
        if (p3 == pc)
            pend = p4;
        else
            pend = p3;
        radius = 40;
        m_name = getAngleName();//"[" + p1.m_name + p2.m_name + "," + p3.m_name + p4.m_name + "]";
        ptext = new GEText(this, 2, 2, GEText.CNAME_TEXT);
        ptext.setFont(UtilityMiscellaneous.angleNameFont);
        ptext.setText1(m_name);
        ftype = UtilityMiscellaneous.ANGLE_TYPE;
        value1 = 1;
    }


    public void setAngleText(String s) {
        if (ptext != null)
            ptext.setText(s);
    }

    public String getAngleName() {
        GEPoint p0;
        if (p1 != null || p2 != null || p3 != null || p4 != null) {
            if (p1 == p4) {
                p0 = p4;
                p4 = p3;
                p3 = p0;
            } else if (p2 == p3) {
                p0 = p1;
                p1 = p2;
                p2 = p0;
            } else if (p2 == p4) {
                p0 = p1;
                p1 = p2;
                p2 = p0;
                p0 = p4;
                p4 = p3;
                p3 = p0;
            }
            if (p1 != p3)
                return Cm.sangle + "[" + (p1) + (p2) + "," + (p3) + (p4) + "]";
            else
                return Cm.sangle + "[" + (p2) + (p3) + (p4) + "]";
        } else {
            GEPoint pt = GELine.commonPoint(lstart, lend);
            if (pt != null)
                return Cm.sangle + "[" + (pstart) + (pt) + (pend) + "]";
            else {
                GEPoint p1 = lstart.getAPointBut(pstart);
                GEPoint p2 = lend.getAPointBut(pend);
                return Cm.sangle + "[" + (p1) + (pstart) + "," + (p2) + (pend) + "]";
            }
        }
    }

    public GEAngle(GELine l1, GELine l2, GEPoint p1, GEPoint p2) {

        super(GraphicEntity.ANGLE);

        lstart = l1;
        lend = l2;
        double[] r = GELine.Intersect(l1, l2);


        if (r == null) {
            UtilityMiscellaneous.print("line can not intersect");
        } else {
            double x = p1.getx();
            double y = p1.gety();
            GEPoint p11 = null;
            for (int i = 0; i < l1.getPtsSize(); i++) {
                GEPoint p = l1.points.get(i);
                if ((p.getx() - r[0]) * (x - r[0]) + (p.gety() - r[1]) * (y - r[1]) > UtilityMiscellaneous.ZERO) {
                    if (p11 == null)
                        p11 = p;
                    else if (p11.x1.xindex > p.x1.xindex)
                        p11 = p;
                }

            }
            pstart = p11;
            if (pstart == null)
                pstart = l1.getFirstPoint();
            x = p2.getx();
            y = p2.gety();
            p11 = null;

            for (int i = 0; i < l2.getPtsSize(); i++) {
                GEPoint p = l2.points.get(i);
                if ((p.getx() - r[0]) * (x - r[0]) + (p.gety() - r[1]) * (y - r[1]) > UtilityMiscellaneous.ZERO) {
                    if (p11 == null)
                        p11 = p;
                    else if (p11.x1.xindex > p.x1.xindex)
                        p11 = p;
                }
            }
            pend = p11;
            if (pend == null)
                pend = l2.getFirstPoint();
        }
        if (pstart == null || pend == null) {
            UtilityMiscellaneous.print("CAngle contruction error");
        }

        radius = 40;
        m_name = this.getAngleName();

        ptext = new GEText(this, 0, 0, GEText.CNAME_TEXT);
        ptext.setFont(UtilityMiscellaneous.angleNameFont);
        ptext.setText1(m_name);
        ftype = UtilityMiscellaneous.ANGLE_TYPE;
        value1 = 1;
    }

    public void setRadius(int v) {
        radius = v;
    }

    public int getRadius() {
        return radius;
    }

    public void setAngleName(String s) {
        if (ptext != null)
            ptext.setText(s);
    }

    public GEText getText() {
        return ptext;
    }

    public double getxForString() {
        return dx;
    }

    public double getyForString() {
        return dy;
    }

    public String TypeString() {
        if (m_name == null) return "";
        return m_name;
    }

    public GEPoint CrossPoint() {
        return GELine.commonPoint(this.lstart, this.lend);
    }

    public String getDescription() {
        GEPoint p = GELine.commonPoint(this.lstart, this.lend);
        if (p != null)
            return "[" + pstart.m_name + p.m_name + pend.m_name + "]";
        else {
            return "[" + lstart.getDescription2() + "," + lend.getDescription2() + "]"; // read error here.
        }
    }

    public GEPoint getVertex() {
        return GELine.commonPoint(this.lstart, this.lend);
    }

    public static GELine getCrossLine(GEAngle g1, GEAngle g2) {
        if (g1.lstart == g2.lstart || g1.lstart == g2.lend) return g1.lstart;
        if (g1.lend == g2.lstart || g1.lend == g2.lend) return g1.lend;
        return null;
    }

    public static GEPoint canEqual(GEAngle ag1, GEAngle ag2) {
        GELine ln1 = ag1.lstart;
        GELine ln2 = ag1.lend;
        GELine ln3 = ag2.lstart;
        GELine ln4 = ag2.lend;

        GEPoint pa = GELine.commonPoint(ln1, ln2);
        GEPoint pb = GELine.commonPoint(ln3, ln4);
        if (pa == null || pb == null) return null;
        GEPoint p1 = ln1.getPointOtherThan(pa);
        GEPoint p2 = ln2.getPointOtherThan(pa);
        GEPoint p3 = ln3.getPointOtherThan(pb);
        GEPoint p4 = ln4.getPointOtherThan(pb);
        if (p1 == null || p2 == null || p3 == null || p4 == null) return null;
        GEPoint p = p1;
        if (p.x1.xindex < p2.x1.xindex)
            p = p2;
        if (p.x1.xindex < p3.x1.xindex)
            p = p3;
        if (p.x1.xindex < p4.x1.xindex)
            p = p4;
        if (p.x1.Solved && p.y1.Solved)
            return null;
        return p;
    }

    public boolean isLocatedNear(double x, double y) {
        if (bVisible == false) return false;

        double[] r = GELine.Intersect(lstart, lend);
        if (r == null) return false;

        if (Math.abs(Math.sqrt(Math.pow(x - r[0], 2) + Math.pow(y - r[1], 2)) - radius) > UtilityMiscellaneous.PIXEPS)
            return false;


        double dx1 = pstart.getx() - r[0];
        double dy1 = pstart.gety() - r[1];
        dy1 = (-1) * dy1;

        double k1 = dy1 / dx1;
        double r1 = Math.atan(k1);
        if (dx1 < 0)
            r1 += Math.PI;


        double dx2 = pend.getx() - r[0];
        double dy2 = pend.gety() - r[1];

        dy2 = (-1) * dy2;
        double k2 = dy2 / dx2;
        double r2 = Math.atan(k2);

        if (dx2 < 0)
            r2 += Math.PI;


        double dr = (r2 - r1);

        if (dr > Math.PI)
            dr = dr - 2 * Math.PI;
        else if (dr < -Math.PI) dr = dr + 2 * Math.PI;

        double dx3 = x - r[0];
        double dy3 = -(y - r[1]);
        double k3 = (dy3) / dx3;
        double r3 = Math.atan(k3);
        if (dx3 < 0)
            r3 += Math.PI;
        double dr1 = (r3 - r1);

        if (dr1 > Math.PI)
            dr1 = dr1 - 2 * Math.PI;
        else if (dr1 < -Math.PI) dr1 = dr1 + 2 * Math.PI;

        if (dr1 * dr > 0 && Math.abs(dr1) < Math.abs(dr)) return true;

        return false;
    }

    public void move(double dx, double dy) {
        double[] r = GELine.Intersect(lstart, lend);
        if (r == null) return;

        this.radius = (int) Math.sqrt(Math.pow(dx - r[0], 2) + Math.pow(dy - r[1], 2));
        if (radius < 20)
            radius = 20;
    }


//    public void draw(Graphics2D g2) {
//        if (bVisible == false) return;
//        draw(g2, false);
//    }


    public void drawauxLine1(double x, double y, GEPoint p, GEPoint p1, GEPoint p2, Graphics2D g2) {
        double dx1 = p.getx() - x;
        double dy1 = p.gety() - y;
        double r = Math.sqrt(dx1 * dx1 + dy1 * dy1);
        dx1 /= r;
        dy1 /= r;
        dx1 = dx1 * radius + x;
        dy1 = dy1 * radius + y;

        if ((dx1 - x) * (dx1 - p1.getx()) <= 0 || (dx1 - x) * (dx1 - p2.getx()) <= 0 || (p1.getx() - dx1) * (p2.getx() - dx1) <= 0)
            return;

        double t1 = Math.pow(x - dx1, 2) + Math.pow(y - dy1, 2);
        double t2 = Math.pow(p1.getx() - dx1, 2) + Math.pow(p1.gety() - dy1, 2);
        double t3 = Math.pow(p2.getx() - dx1, 2) + Math.pow(p2.gety() - dy1, 2);
        double xf, yf;
        if (t1 <= t2 && t1 <= t3) {
            xf = x;
            yf = y;
        } else if (t2 <= t1 && t2 <= t3) {
            xf = p1.getx();
            yf = p1.gety();
        } else //if(t3 <= t1 && t3 <= t2)
        {
            xf = p2.getx();
            yf = p2.gety();
        }
        g2.setColor(Color.red);
        g2.setStroke(UtilityMiscellaneous.DashedStroke);
        g2.drawLine((int) dx1, (int) dy1, (int) xf, (int) yf);
    }

    public void drawauxLine(double x, double y, Graphics2D g2) {
        if (Math.abs(x) > 2000 || Math.abs(y) > 2000) return;
        GEPoint pl1[] = lstart.getMaxMinPoint();
        GEPoint pl2[] = lend.getMaxMinPoint();
        if (pl1 == null || pl2 == null) return;
        if (lstart.inside(x, y) && lend.inside(x, y))
            return;

        if (GELine.commonPoint(lstart, lend) == null) {
            GEPoint p1, p2;
            if (Math.pow(x - pl1[0].getx(), 2) + Math.pow(y - pl1[0].gety(), 2) < Math.pow(x - pl1[1].getx(), 2) + Math.pow(y - pl1[1].gety(), 2))
                p1 = pl1[0];
            else
                p1 = pl1[1];

            if (Math.pow(x - pl2[0].getx(), 2) + Math.pow(y - pl2[0].gety(), 2) < Math.pow(x - pl2[1].getx(), 2) + Math.pow(y - pl2[1].gety(), 2))
                p2 = pl2[0];
            else
                p2 = pl2[1];
            g2.setColor(Color.red);
            g2.setStroke(UtilityMiscellaneous.DashedStroke);
            if (!lstart.inside(x, y))
                g2.drawLine((int) x, (int) y, (int) p1.getx(), (int) p1.gety());
            if (!lend.inside(x, y))
                g2.drawLine((int) x, (int) y, (int) p2.getx(), (int) p2.gety());
        }
        drawauxLine1(x, y, pstart, pl1[0], pl1[1], g2);
        drawauxLine1(x, y, pend, pl2[0], pl2[1], g2);

    }

    public void setShowType(int n) {
        show_type = n;
    }

    public void draw(Graphics2D g2, boolean isSelected) {
        if (!this.isdraw()) return;
        double[] r = GELine.Intersect(lstart, lend);
        if (r == null) return;
        drawauxLine(r[0], r[1], g2);
        double dx1 = pstart.getx() - r[0];
        double dy1 = pstart.gety() - r[1];
        dy1 = (-1) * dy1;

        double k1 = dy1 / dx1;
        double r1 = Math.atan(k1);
        if (dx1 < 0)
            r1 += Math.PI;

        double t = Math.sqrt(dx1 * dx1 + dy1 * dy1);
        dx1 = dx1 / t;
        dy1 = dy1 / t;


        double dx2 = pend.getx() - r[0];
        double dy2 = pend.gety() - r[1];

        dy2 = (-1) * dy2;
        double k2 = dy2 / dx2;
        double r2 = Math.atan(k2);

        if (dx2 < 0)
            r2 += Math.PI;

        t = Math.sqrt(dx2 * dx2 + dy2 * dy2);
        dx2 = dx2 / t;
        dy2 = dy2 / t;


        double jx = dx2 * radius + r[0];
        double jy = (-1) * dy2 * radius + r[1];


        double ri1 = (180 * r1 / Math.PI);
        double dr = (180 * (r2 - r1) / Math.PI);

        double alpha = -Math.PI / 3;

        if (dr > 180)
            dr = dr - 360;
        else if (dr < -180) dr = dr + 360;

//        if (dr >= 0)
//            dr += 0.5;
//        else dr -= 0 / 5;


        double rc = dr * Math.PI / (360);
        double cx = (dx1 * Math.cos(rc) - dy1 * Math.sin(rc)) * (radius + 6) + r[0];
        double cy = -(dy1 * Math.cos(rc) + dx1 * Math.sin(rc)) * (radius + 6) + r[1];

        dx = cx;
        dy = cy;


        if (isSelected) {
            prepareToBeDrawnAsSelected(g2);
            g2.drawArc((int) r[0] - radius, (int) r[1] - radius, radius * 2, radius * 2, (int) ri1, (int) dr);
            return;
        } else
            super.prepareToBeDrawnAsUnselected(g2);

        double dr1 = dr;

        if (ftype == 1) {
            double lr = 13;

            if (dr > 0) {
                alpha = -alpha;
                dr = (dr - 180 * Math.cos(Math.PI / 2.0 - alpha) * lr * 1 / (3 * Math.PI * radius));
                if (dr < 0) dr = 0;
            } else {
                dr = (dr + 180 * Math.cos(Math.PI / 2.0 + alpha) * lr * 1 / (3 * Math.PI * radius));
                if (dr > 0)
                    dr = 0;
            }
            dy2 = -dy2;

            double c1 = Math.cos(alpha);
            double s1 = Math.sin(alpha);
            double c2 = Math.cos(Math.PI - alpha);
            double s2 = Math.sin(Math.PI - alpha);


            double jx1 = (dx2 * c1 - dy2 * s1) * lr + jx;
            double jy1 = (dy2 * c1 + dx2 * s1) * lr + jy;
            double jx2 = (dx2 * c2 - dy2 * s2) * lr + jx;
            double jy2 = (dy2 * c2 + dx2 * s2) * lr + jy;

            int[] xp = new int[4];
            int[] yp = new int[4];
            xp[0] = (int) jx;
            xp[1] = (int) jx1;
            xp[2] = (int) (jx1 / 3 + jx2 / 3 + jx / 3);
            xp[3] = (int) jx2;
            yp[0] = (int) jy;
            yp[1] = (int) jy1;
            yp[2] = (int) (jy1 / 3 + jy2 / 3 + jy / 3);
            yp[3] = (int) jy2;
            g2.fillPolygon(xp, yp, 4);
        }

        int x = (int) r[0] - radius;
        int y = (int) r[1] - radius;
        int w = radius * 2;


        if (ftype == 2) {
            for (int i = 1; i < value1; i++) {
                g2.drawArc(x + i * 4, y + i * 4, w - i * 8, w - i * 8, (int) ri1, (int) dr);
            }
        } else if (ftype == 3) {
            Composite ac = g2.getComposite();
            g2.setComposite(UtilityMiscellaneous.getFillComposite());

            g2.setColor(DrawData.getColor(value1));
            g2.fillArc(x, y, w, w, (int) ri1, (int) dr);
            g2.setComposite(ac);
            g2.setColor(super.getColor());
        }
        g2.drawArc(x, y, w, w, (int) ri1, (int) dr);


        if (UtilityMiscellaneous.show_angle_text) {
            if (ptext != null) {
                if (show_type == -1 || show_type == 1)
                    ptext.setText1(" " + roundValue(dr1));
                else if (show_type == 0)
                    ptext.setText1(null);
                else if (show_type == 2)
                    ptext.setText1(TypeString());
                else if (show_type == 3)
                    ptext.setText1(this.TypeString() + " = " + roundValue(dr1));
            }
        }
    }

    public float roundValue(double r) {
        float n = (float) r * 10;
        if (n > 0)
            n = n + 0.5f;
        else
            n = n - 0.5f;
        float x = (int) n;
        return x / 10.0f;
    }


    public void SavePS(FileOutputStream fp, int stype) throws IOException {
        if (bVisible == false) return;

        double[] r = GELine.Intersect(lstart, lend);
        if (r == null) return;


        double dx1 = pstart.getx() - r[0];
        double dy1 = pstart.gety() - r[1];
        dy1 = (-1) * dy1;

        double k1 = dy1 / dx1;
        double r1 = Math.atan(k1);
        if (dx1 < 0)
            r1 += Math.PI;

        double t = Math.sqrt(dx1 * dx1 + dy1 * dy1);
        dx1 = dx1 / t;
        dy1 = dy1 / t;


        double dx2 = pend.getx() - r[0];
        double dy2 = pend.gety() - r[1];

        dy2 = (-1) * dy2;
        double k2 = dy2 / dx2;
        double r2 = Math.atan(k2);

        if (dx2 < 0)
            r2 += Math.PI;

        t = Math.sqrt(dx2 * dx2 + dy2 * dy2);
        dx2 = dx2 / t;
        dy2 = dy2 / t;


        double jx = dx2 * radius + r[0];
        double jy = (-1) * dy2 * radius + r[1];


        //int ri1 = (int) (180 * r1 / Math.PI);
        double dr = (180 * (r2 - r1) / Math.PI);
        //double tr = dr;

        double alpha = -Math.PI / 3;


        if (dr > 180)
            dr = dr - 360;
        else if (dr < -180) dr = dr + 360;


        double rc = dr * Math.PI / (360);

        double cx = (dx1 * Math.cos(rc) - dy1 * Math.sin(rc)) * (radius + 6) + r[0];
        double cy = -(dy1 * Math.cos(rc) + dx1 * Math.sin(rc)) * (radius + 6) + r[1];

        dx = cx;
        dy = cy;

        double lr = 12.0;
        //double dr1 = dr;

        if (dr > 0) {
            alpha = -alpha;
            dr = (dr - 180 * Math.cos(Math.PI / 2.0 - alpha) * lr * 1 / (3 * Math.PI * radius));
            if (dr < 0) dr = 0;
        } else {
            dr = (dr + 180 * Math.cos(Math.PI / 2.0 + alpha) * lr * 1 / (3 * Math.PI * radius));
            if (dr > 0)
                dr = 0;
        }
        dy2 = -dy2;

        double c1 = Math.cos(alpha);
        double s1 = Math.sin(alpha);
        double c2 = Math.cos(Math.PI - alpha);
        double s2 = Math.sin(Math.PI - alpha);


        double jx1 = (dx2 * c1 - dy2 * s1) * lr + jx;
        double jy1 = (dy2 * c1 + dx2 * s1) * lr + jy;
        double jx2 = (dx2 * c2 - dy2 * s2) * lr + jx;
        double jy2 = (dy2 * c2 + dx2 * s2) * lr + jy;
        int[] xp = new int[4];
        int[] yp = new int[4];
        xp[0] = (int) jx;
        xp[1] = (int) jx1;
        xp[2] = (int) (jx1 / 3 + jx2 / 3 + jx / 3);
        xp[3] = (int) jx2;
        yp[0] = -(int) jy;
        yp[1] = -(int) jy1;
        yp[2] = -(int) (jy1 / 3 + jy2 / 3 + jy / 3);
        yp[3] = -(int) jy2;

        float start = (float) (180 * r1 / Math.PI);
        float end = (float) (180 * r2 / Math.PI);
        start = round(start);
        end = round(end);
//        double rd = round(end - start);

//        float end = (float) (ri1 + dr);

        if (end - start >= 180)
            end -= 360;
        else if (start - end >= 180)
            end += 360;
        if (end < start) {
            float tp = end;
            end = start;
            start = tp;
        }


        if (ftype == 1) {
            String s = "";
            s += xp[0] + " " + yp[0] + " moveto " + xp[1] + " " + yp[1] + " lineto " + xp[2] + " " + yp[2] + " lineto "
                    + xp[3] + " " + yp[3] + " lineto " + "closepath " + " Color" + new Integer(m_color).toString() + " "
                    + "fill stroke\n";
            fp.write(s.getBytes());
        } else if (ftype == 2) {
            int w = radius;
            for (int i = 1; i < value1; i++) {
                String s = (int) r[0] + " " + (int) (-r[1]) + " " + (w - 4 * i) + " " + start + " " + end + " arc\n";// stroke\n\n";
                fp.write(s.getBytes());
            }
        } else if (ftype == 3) {
            int a = (int) r[0];
            int b = (int) (-r[1]);

            String s = "newpath " + a + " " + b + " " + radius + " " + start + " " + end + " arc "
                    + a + " " + b + " lineto Color" + value1 + " closepath fill\n";
            fp.write(s.getBytes());
        }

        String ss = "";
        ss += (int) r[0] + " " + (int) (-r[1]) + " " + radius + " " + start + " " + end + " arc";// stroke\n\n";
        fp.write(ss.getBytes());
        this.saveSuper(fp);
    }

    public float round(float r) {
        while (r >= 180)
            r -= 360;
        while (r < -180)
            r += 360;
        return r;
    }

    public static double get3pAngle(double x1, double y1, double x2, double y2, double x3, double y3) {
        return get4pAngle(x1, y1, x2, y2, x2, y2, x3, y3);
    }

    public static double get4pAngle(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {

        double dx1 = x1 - x2;
        double dy1 = y1 - y2;
        dy1 = (-1) * dy1;

        double k1 = dy1 / dx1;
        double r1 = Math.atan(k1);
        if (dx1 < 0)
            r1 += Math.PI;

        double dx2 = x3 - x4;
        double dy2 = y3 - y4;
        dy2 = (-1) * dy2;
        double k2 = dy2 / dx2;
        double r2 = Math.atan(k2);

        if (dx2 < 0)
            r2 += Math.PI;

        double dr = ((r1 - r2));
        if (dr > Math.PI || Math.abs(dr - Math.PI) < UtilityMiscellaneous.ZERO)
            dr = dr - Math.PI * 2;
        else if (dr < -Math.PI || Math.abs(dr + Math.PI) < UtilityMiscellaneous.ZERO)
            dr = dr + Math.PI * 2;
        return dr;
    }

    public static double getAngleValue(GEPoint pa, GEPoint pb, GEPoint pc) {
        if (pa == null || pb == null || pc == null) return 0.0;
        return get3pAngle(pa.getx(), pa.gety(), pb.getx(), pb.gety(), pc.getx(), pc.gety());
    }

    public static double getAngleValue(GEPoint p1, GEPoint p2, GEPoint p3, GEPoint p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) {
            return 0.0;
        }
        return get4pAngle(p1.getx(), p1.gety(), p2.getx(), p2.gety(), p3.getx(), p3.gety(), p4.getx(), p4.gety());
    }

@Override
public Element saveIntoXMLDocument(Element rootElement, String sTypeName) {
	assert(rootElement != null);
	if (rootElement != null) {
		Element elementThis = super.saveIntoXMLDocument(rootElement, "angle");

		elementThis.setAttribute("start_line", String.valueOf(lstart.m_id));
		elementThis.setAttribute("end_line", String.valueOf(lend.m_id));
		elementThis.setAttribute("start_point", String.valueOf(pstart.m_id));
		elementThis.setAttribute("end_point", String.valueOf(pend.m_id));
		elementThis.setAttribute("radius", String.valueOf(radius));
		elementThis.setAttribute("show_type", String.valueOf(show_type));
		elementThis.setAttribute("ftype", String.valueOf(ftype));
		elementThis.setAttribute("value1", String.valueOf(value1));

		return elementThis;
	}
	return null;
}    
    
//    public void Save(DataOutputStream out) throws IOException {
//        super.Save(out);
//
//        out.writeInt(lstart.m_id);
//        out.writeInt(lend.m_id);
//        out.writeInt(pstart.m_id);
//        out.writeInt(pend.m_id);
//        out.writeInt(radius);
//        out.writeInt(show_type);
//        out.writeInt(ftype);
//        out.writeInt(value1);
//    }
//
//    public void Load(DataInputStream in, drawProcess dp) throws IOException {
//
//        if (CMisc.version_load_now < 0.01) {
//            m_id = in.readInt();
//            int dx = in.readInt();
//            lstart = dp.getLineByid(dx);
//            int dy = in.readInt();
//            lend = dp.getLineByid(dy);
//            radius = in.readInt();
//
//            int px = in.readInt();
//            pstart = dp.getPointById(px);
//            int py = in.readInt();
//            pend = dp.getPointById(py);
//
//            drawType drawt = new drawType();
//            drawt.Load(in);
//
//            m_color = drawt.color_index;
//            if (m_color == 1)
//                m_color = 3;
//            m_dash = drawt.dash;
//            m_width = drawt.width;
//
//        } else {
//            super.Load(in, dp);
//
//            int dx = in.readInt();
//            lstart = dp.getLineByid(dx);
//            int dy = in.readInt();
//            lend = dp.getLineByid(dy);
//            int px = in.readInt();
//            pstart = dp.getPointById(px);
//            int py = in.readInt();
//            pend = dp.getPointById(py);
//            radius = in.readInt();
//            if (CMisc.version_load_now >= 0.042) {
//                show_type = in.readInt();
//                ftype = in.readInt();
//                value1 = in.readInt();
//            }
//        }
//    }
}
