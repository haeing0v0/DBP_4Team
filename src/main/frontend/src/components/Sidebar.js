import React, { useState, useEffect } from 'react';
import { NavLink, useLocation } from 'react-router-dom';
import '../styles/Sidebar.css';
import { LuHouse } from "react-icons/lu";
import { MdOutlineLibraryAddCheck } from "react-icons/md";
import { LuUsersRound } from "react-icons/lu";
import { TbMoneybag } from "react-icons/tb";
import { IoMdArrowDropdown, IoMdArrowDropup } from "react-icons/io";

function Sidebar() {
  const location = useLocation();
  const [expandedMenu, setExpandedMenu] = useState(null);

  useEffect(() => {
    // 현재 경로에 따라 메뉴를 열어줍니다.
    if (location.pathname.startsWith('/check')) {
      setExpandedMenu('attendance');
    } else if (location.pathname.startsWith('/departments')) {
      setExpandedMenu('departments');
    } else if (location.pathname.startsWith('/salary')) {
      setExpandedMenu('salary');
    } else {
      setExpandedMenu(null);
    }
  }, [location.pathname]);

  const toggleSubMenu = (menu) => {
    setExpandedMenu(expandedMenu === menu ? null : menu);
  };

  return (
    <div className="sidebar">
      <h2 className="sidebar-title">DBP 인사 관리 시스템</h2>
      <nav className="sidebar-nav">
        <ul>
          <li>
            <NavLink to="/" activeClassName="active" className="menu-item">
              <div className="menu-content">
                <LuHouse /> Home
              </div>
            </NavLink>
          </li>
          <li>
            <a onClick={() => toggleSubMenu('attendance')} className="menu-item">
              <div className="menu-content">
                <MdOutlineLibraryAddCheck /> 출퇴근 관리
              </div>
              {expandedMenu === 'attendance' ? <IoMdArrowDropup /> : <IoMdArrowDropdown />}
            </a>
            {expandedMenu === 'attendance' && (
              <ul>
                <li>
                  <NavLink to="/check-in" activeClassName="active">
                    출퇴근 등록
                  </NavLink>
                </li>
                <li>
                  <NavLink to="/check" activeClassName="active">
                    출퇴근 조회
                  </NavLink>
                </li>
              </ul>
            )}
          </li>
          <li>
            <a onClick={() => toggleSubMenu('departments')} className="menu-item">
              <div className="menu-content">
                <LuUsersRound /> 부서
              </div>
              {expandedMenu === 'departments' ? <IoMdArrowDropup /> : <IoMdArrowDropdown />}
            </a>
            {expandedMenu === 'departments' && (
              <ul>
                <li>
                  <NavLink to="/departments" activeClassName="active">
                    부서별 직원
                  </NavLink>
                </li>
                <li>
                  <NavLink to="/positions" activeClassName="active">
                    직급 목록
                  </NavLink>
                </li>
                <li>
                  <NavLink to="/register" activeClassName="active">
                    직원 등록
                  </NavLink>
                </li>
              </ul>
            )}
          </li>
          <li>
            <a onClick={() => toggleSubMenu('salary')} className="menu-item">
              <div className="menu-content">
                <TbMoneybag /> 급여
              </div>
              {expandedMenu === 'salary' ? <IoMdArrowDropup /> : <IoMdArrowDropdown />}
            </a>
            {expandedMenu === 'salary' && (
              <ul>
                <li>
                  <NavLink to="/salary" activeClassName="active">
                    급여 조회
                  </NavLink>
                </li>
                <li>
                  <NavLink to="/incentives" activeClassName="active">
                    인센티브 등록
                  </NavLink>
                </li>
              </ul>
            )}
          </li>
        </ul>
      </nav>
    </div>
  );
}

export default Sidebar;
