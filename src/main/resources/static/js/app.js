/**
 * 우리 앱의 진입점 js 파일
 */
import {authService} from "./utils/auth.js";

// 현재 페이지 확인 함수
const getCurrentPage = () => {

    const path = window.location.pathname;
    if (path === '/login') return 'login';
    if (path === '/signup') return 'signup';
    if (path === '/dashboard') return 'dashboard';
    if (path === '/') return 'home';
    return 'default';

}

const App = () => {

    // 외부 모듈 js 파일들을 로드
    // 현재 어떤 페이지에 진입했는지 알아야 그에 맞는 js를 가져올 수 있음
    const currentPage = getCurrentPage();

    // 공통 이벤트 바인딩
    const bindEvents = () => {
        // 로그아웃 이벤트
        document.addEventListener('click', e => {
            if(e.target.closest('[data-action="logout"]')) {
                e.preventDefault();
                // 실제 로그아웃 처리 (localStorage에 있는 로그인 관련 데이터 삭제)
                authService.logout();
            }
        })
    }

    const init = async () => {
        try {

            // header 인증 UI 업데이트
            authService.updateHeaderUI();

            // 앱의 공통 이벤트 바인딩
            bindEvents();

            const module = await import(`./pages/${currentPage}.js`);
            // console.log(module);

            if(module) {
                // default() 함수는 export default 내보낸 함수의 리턴값을 가져온다.
                const component = module.default();
                component.init(); // 서브 모듈 실행
            }

        } catch (error) {
            // console.log(`페이지 모듈 ${currentPage} 로드 실패!`, error);
        }
    }

    init();

}

// 기본 JavaScript 파일
document.addEventListener('DOMContentLoaded', () => {
    console.log('여행 기록 관리 시스템이 로드되었습니다.');
    App();
});
