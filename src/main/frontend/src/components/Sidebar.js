import React, { useState } from 'react';
import '../styles/Sidebar.css';
import { LuHouse } from "react-icons/lu";
import { MdOutlineLibraryAddCheck } from "react-icons/md";
import { LuUsersRound } from "react-icons/lu";
import { TbMoneybag } from "react-icons/tb";

function Sidebar() {
  const [activeLink, setActiveLink] = useState('/home');

  const handleLinkClick = (path) => {
    setActiveLink(path);
  };

  return (
    <div className="sidebar">
      <h2 className="sidebar-title">DBP 인사 관리 시스템</h2>
      <nav className="sidebar-nav">
        <ul>
          <li>
            <a
              href="/home"
              className={activeLink === '/home' ? 'active' : ''}
              onClick={() => handleLinkClick('/home')}
            >
              <LuHouse />Home
            </a>
          </li>
          <li>
            <a
              href="#"
            //   className={activeLink === '/attendance' ? 'active' : ''}
              onClick={() => handleLinkClick('/check-in')}
            >
              <MdOutlineLibraryAddCheck />출퇴근 관리
            </a>
            <ul>
              <li>
                <a
                  href="/check-in"
                  className={activeLink === '/check-in' ? 'active' : ''}
                  onClick={() => handleLinkClick('/check-in')}
                >
                  출퇴근 등록
                </a>
              </li>
              <li>
                <a
                  href="/attendance"
                  className={activeLink === '/attendance' ? 'active' : ''}
                  onClick={() => handleLinkClick('/attendance')}
                >
                  출퇴근 조회
                </a>
              </li>
            </ul>
          </li>
          <li>
            <a
              href="#"
            //   className={activeLink === '/departments' ? 'active' : ''}
              onClick={() => handleLinkClick('/departments')}
            >
              <LuUsersRound />부서
            </a>
            <ul>
              <li>
                <a
                  href="/departments"
                //   className={activeLink === '/departments' ? 'active' : ''}
                  onClick={() => handleLinkClick('/departments')}
                >
                  부서별 직원
                </a>
              </li>
              <li>
                <a
                  href="/positions"
                  className={activeLink === '/positions' ? 'active' : ''}
                  onClick={() => handleLinkClick('/positions')}
                >
                  직급 목록
                </a>
              </li>
              <li>
                <a
                  href="/register"
                  className={activeLink === '/register' ? 'active' : ''}
                  onClick={() => handleLinkClick('/register')}
                >
                  직원 등록
                </a>
              </li>
            </ul>
          </li>
          <li>
            <a
              href="#"
              className={activeLink === '/salary' ? 'active' : ''}
              onClick={() => handleLinkClick('/salary')}
            >
              <TbMoneybag />급여
            </a>
            <ul>
              <li>
                <a
                  href="/salary"
                  className={activeLink === '/salary' ? 'active' : ''}
                  onClick={() => handleLinkClick('/salary')}
                >
                  급여 조회
                </a>
              </li>
              <li>
                <a
                  href="/incentives"
                  className={activeLink === '/incentives' ? 'active' : ''}
                  onClick={() => handleLinkClick('/incentives')}
                >
                  인센티브 등록
                </a>
              </li>
            </ul>
          </li>
        </ul>
      </nav>
    </div>
  );
}

export default Sidebar;
