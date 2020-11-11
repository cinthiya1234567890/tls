import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './plate-param.reducer';
import { IPlateParam } from 'app/shared/model/plate-param.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlateParamDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PlateParamDetail = (props: IPlateParamDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { plateParamEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          PlateParam [<b>{plateParamEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="value">Value</span>
          </dt>
          <dd>{plateParamEntity.value}</dd>
          <dt>
            <span id="peckOrder">Peck Order</span>
          </dt>
          <dd>{plateParamEntity.peckOrder}</dd>
          <dt>Query Plate</dt>
          <dd>{plateParamEntity.queryPlateId ? plateParamEntity.queryPlateId : ''}</dd>
          <dt>Cascader</dt>
          <dd>{plateParamEntity.cascaderCascaderName ? plateParamEntity.cascaderCascaderName : ''}</dd>
        </dl>
        <Button tag={Link} to="/plate-param" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/plate-param/${plateParamEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ plateParam }: IRootState) => ({
  plateParamEntity: plateParam.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PlateParamDetail);
