import { useEffect } from 'react';
import { useUserDataStore } from '@/stores/useUserDataStore';

const AddressInput = ({ disabled }) => {
  const {
    userData: { memberDTO },
    setMemberField,
  } = useUserDataStore();

  useEffect(() => {
    const script = document.createElement('script');
    script.src = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js';
    script.async = true;
    document.head.appendChild(script);
    return () => {
      document.head.removeChild(script);
    };
  }, []);

  const handleAddressSearch = () => {
    new window.daum.Postcode({
      oncomplete: function (data) {
        setMemberField('zipcode', data.zonecode);
        setMemberField('address', data.address);
        // 주소 정보를 useUserDataStore에도 저장
        document.querySelector('input[name=address_detail]').focus();
      },
      width: '100%',
      height: '100%',
    }).open();
  };

  const handleAddressDetailChange = e => {
    setMemberField('addressDetail', e.target.value);
  };

  return (
    <div className='block'>
      <span className='mb-1 block text-sm font-medium text-slate-700'>주소</span>
      <div className='mb-2 flex'>
        <input
          type='text'
          name='zipcode'
          value={memberDTO.zipcode}
          className={`${disabled ? 'bg-ipt_disa ' : 'bg-white'} flex-grow min-w-0 text-sm rounded-md border border-slate-300  px-3 py-2 placeholder-slate-400 shadow-sm placeholder:text-sm focus:border-mint focus:outline-none focus:ring-1 focus:ring-mint sm:text-sm h-10`}
          placeholder='우편번호'
          readOnly
          disabled={disabled}
          autoComplete='postal-code'
        />
        <button
          onClick={handleAddressSearch}
          className={`rounded-md ml-1 px-4 h-10 flex items-center justify-center ${
            disabled ? 'bg-gray-100 text-gray-400 cursor-not-allowed' : 'bg-gray-200 cursor-pointer'
          }`}
          type='button'
          disabled={disabled}>
          🔍
        </button>
      </div>
      <input
        type='text'
        name='address'
        value={memberDTO.address}
        className={`${disabled ? 'bg-ipt_disa ' : 'bg-white'} mb-2 w-full text-sm  rounded-md border border-slate-300  px-3 py-2 placeholder-slate-400 shadow-sm placeholder:text-sm focus:border-mint focus:outline-none focus:ring-1 focus:ring-mint sm:text-sm`}
        placeholder='주소'
        readOnly
        disabled={disabled}
        autoComplete='street-address'
      />
      <input
        type='text'
        name='address_detail'
        value={memberDTO.addressDetail}
        onChange={handleAddressDetailChange}
        className={`${disabled ? 'bg-ipt_disa ' : 'bg-white'} w-full text-sm rounded-md border border-slate-300  px-3 py-2 placeholder-slate-400 shadow-sm placeholder:text-sm focus:border-mint focus:outline-none focus:ring-1 focus:ring-mint sm:text-sm`}
        placeholder='상세 주소'
        disabled={disabled}
        autoComplete='address-line2'
      />
    </div>
  );
};

export default AddressInput;
