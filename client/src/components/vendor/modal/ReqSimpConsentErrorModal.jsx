import { formatId } from '@/utils/format/formatId';
import { formatPhone } from '@/utils/format/formatPhone';
import { Tooltip } from 'react-tooltip';
import 'react-tooltip/dist/react-tooltip.css';
import BaseModal from '@/components/common/BaseModal';
import { FaExclamationCircle } from 'react-icons/fa';

const ReqSimpConsentErrorModal = ({ errors, isShowModal, icon, setIsShowModal, modalTitle }) => {
  return (
    <BaseModal
      isShowModal={isShowModal}
      setIsShowModal={setIsShowModal}
      modalTitle={modalTitle}
      icon={icon}
      height={'h-5/6'}
      width={'w-5/6'}>
      <div className='flex flex-col h-full'>
        <div className='mb-4 text-lg font-semibold text-red-500'>
          {errors && errors[0].total}건 중 {errors ? errors.length : '-'}건의 간편동의 요청이
          실패했습니다.
        </div>
        <div className='flex-grow overflow-auto'>
          <table className='w-full mb-3'>
            <thead>
              <tr className='bg-table_col'>
                <th className='p-2 text-left text-text_black w-8' />
                <th className='p-2 text-left text-text_black'>회원이름</th>
                <th className='p-2 text-left text-text_black'>휴대전화</th>
                <th className='p-2 text-left text-text_black'>결제방식</th>
                <th className='p-2 text-left text-text_black'>약정일</th>
                <th className='p-2 text-left text-text_black'>계약상태</th>
              </tr>
            </thead>
            <tbody>
              {errors &&
                errors.map(({ from, res }, idx) => (
                  <tr key={idx} className='hover:bg-gray-100'>
                    <td className='border-b border-ipt_border p-2 text-text_black'>
                      <div className='mr-2'>
                        <FaExclamationCircle
                          className='text-red-500 cursor-help'
                          data-tooltip-id={`error-${idx}`}
                          data-tooltip-html={res.message}
                        />
                        <Tooltip id={`error-${idx}`} place='top' type='error' effect='solid' />
                      </div>
                    </td>

                    <td className='border-b border-ipt_border p-2 text-text_black'>
                      {from.memberName}
                    </td>
                    <td className='border-b border-ipt_border p-2 text-text_black'>
                      {formatPhone(from.memberPhone)}
                    </td>
                    <td className='border-b border-ipt_border p-2 text-text_black'>
                      {from.paymentType}
                    </td>
                    <td className='border-b border-ipt_border p-2 text-text_black'>
                      {from.contractDay}
                    </td>
                    <td className='border-b border-ipt_border p-2 text-text_black'>
                      {from.contractStatus}
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
        <div className='mt-4 flex justify-center'>
          <button
            onClick={() => setIsShowModal(false)}
            className='px-4 py-2 bg-mint text-white rounded hover:bg-mint_hover transition-colors'>
            확인
          </button>
        </div>
      </div>
    </BaseModal>
  );
};

export default ReqSimpConsentErrorModal;
