package com.lewjun.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "emp")
public class Emp {
    private Integer empno;

    private String  ename;

    private String  job;

    private Integer mgr;

    private Date    hiredate;

    private Integer deptno;

    public Integer getEmpno() {
        return empno;
    }

    @XmlElement
    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    @XmlElement
    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    @XmlElement
    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    @XmlElement
    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    @XmlElement
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getDeptno() {
        return deptno;
    }

    @XmlElement
    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Emp [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr
               + ", hiredate=" + hiredate + ", deptno=" + deptno + "]";
    }
}