import { privateAxios } from '.';

/* 간편서명동의 데이터 가져오기 */
export const getSimpleConsent = async () => {
  try {
    const res = await privateAxios.get('/v1/vendor/simple-consent');
    return res.data;
  } catch (err) {
    console.err('간편서명동의 데이터 가져오기 실패:', err);
    throw err;
  }
};

/* 간편서명동의 설정 전체 상품리스트 가져오기 */
export const getAllProducts = async () => {
  try {
    const res = await privateAxios.get('/v1/vendor/product/all/no-cond');
    return res.data;
  } catch (err) {
    console.error('모든 상품 가져오기 실패:', err);
    throw err;
  }
};

/* 간편서명동의 설정 업데이트 */
export const updateSimpleConsent = async data => {
  try {
    const res = await privateAxios.put('/v1/vendor/simple-consent', data);
    return res.data;
  } catch (err) {
    console.err('간편서명동의 설정 업데이트 실패:', err);
    throw err;
  }
};

/* 간편서명동의 가능한 옵션 가져오기 */
export const getAvailableOptions = async () => {
  try {
    const res = await privateAxios.get('/v1/vendor/simple-consent/available-options');
    return res.data;
  } catch (err) {
    console.err('간편서명동의 가능한 옵션 가져오기 실패:', err);
    throw err;
  }
};

/* 간편서명동의 회원 데이터 보내기 */
export const sendSimpleConsentData = async userData => {
  try {
    const res = await privateAxios.post('/v1/simple-consent', userData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    return res.data;
  } catch (err) {
    console.err('간편서명동의 회원 데이터 보내기 실패:', err);
    throw err;
  }
};
